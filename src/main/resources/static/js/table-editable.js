var TableEditable = function () {

    var handleTable = function () {

        function restoreRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            console.log(aData);
            var jqTds = $('>td', nRow);

            for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                oTable.fnUpdate(aData[i], nRow, i, false);
            }

            oTable.fnDraw();
        }

        /**
         * 新建一个 字段 开始编辑
         * @param oTable
         * @param nRow
         */
        function editRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            jqTds[0].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[0] + '" disabled>';
            jqTds[1].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[1] + '">';
            jqTds[2].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[2] + '">';
            jqTds[3].innerHTML = '<input type="number" class="form-control input-small" value="' + aData[3] + '">';
            jqTds[4].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[4] + '">';
            jqTds[5].innerHTML = '<a class="edit" href="">保存</a>';
            jqTds[6].innerHTML = '<a class="cancel" href="">取消</a>';
        }

        /**
         * 编辑已经保存过的 字段信息
         * @param nRow
         */
        function editOldRow(nRow) {
            var jqTds = $('>td', nRow);
            var jqInputs = $('input', nRow);
            jqTds[0].innerHTML = '<input type="text" class="form-control input-small" value="' + jqInputs[0].value + '" disabled>';
            jqTds[1].innerHTML = '<input type="text" class="form-control input-small" value="' + jqInputs[1].value + '">';
            jqTds[2].innerHTML = '<input type="text" class="form-control input-small" value="' + jqInputs[2].value + '">';
            jqTds[3].innerHTML = '<input type="number" class="form-control input-small" value="' + jqInputs[3].value + '">';
            jqTds[4].innerHTML = '<input type="text" class="form-control input-small" value="' + jqInputs[4].value + '">';
            jqTds[5].innerHTML = '<a class="edit" href="">保存</a>';
            jqTds[6].innerHTML = '<a class="cancel" href="">取消</a>';

        }

        /**
         * 保存 正在编辑的 字段信息
         * @param oTable
         * @param nRow
         */
        function saveRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            oTable.fnUpdate('<input type="text" class="form-control input-small" name="ledgerDictionaries[' + (jqInputs[0].value - 1) + '].index" value="' + jqInputs[0].value + '" readonly>', nRow, 0, false);
            oTable.fnUpdate('<input type="text" class="form-control input-small" name="ledgerDictionaries[' + (jqInputs[0].value - 1) + '].fieldName" value="' + jqInputs[1].value + '" readonly>', nRow, 1, false);
            oTable.fnUpdate('<input type="text" class="form-control input-small" name="ledgerDictionaries[' + (jqInputs[0].value - 1) + '].fieldType" value="' + jqInputs[2].value + '" readonly>', nRow, 2, false);
            oTable.fnUpdate('<input type="number" class="form-control input-small" name="ledgerDictionaries[' + (jqInputs[0].value - 1) + '].length" value="' + jqInputs[3].value + '" readonly>', nRow, 3, false);
            oTable.fnUpdate('<input type="text" class="form-control input-small" name="ledgerDictionaries[' + (jqInputs[0].value - 1) + '].comment" value="' + jqInputs[4].value + '" readonly>', nRow, 4, false);
            oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 5, false);
            oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, 6, false);
            oTable.fnDraw();
        }

        /**
         * 校验 是否所有的 字段属性域 都已经 填写
         * @param nRow
         */
        function validate(nRow) {
            var jqInputs = $('input', nRow);
            return !(jqInputs[1].value === null || jqInputs[1].value.length <= 0 || jqInputs[2].value === null || jqInputs[2].value.length <= 0);
        }

        var table = $('#sample_editable_1');

        var oTable = table.dataTable({

            // set the initial value
            "pageLength": 10,

            "searching": false,

            "paging": false,

            "ordering": false,

            "info": false

        });

        var nEditing = null;
        var nNew = false;
        var currRowIndex = 0;

        var newBtn = $('#sample_editable_1_new');
        newBtn.click(function (e) {
            e.preventDefault();

            if (nNew && nEditing) {
                if (confirm("您还有相关属性未填写，是否继续填写？")) {
                    return;
                } else {
                    oTable.fnDeleteRow(nEditing); // cancel
                    nEditing = null;
                    nNew = false;
                    currRowIndex--;
                    return;
                }
            }

            var aiNew = oTable.fnAddData([++currRowIndex, '', '', 0, '', '', '']);
            var nRow = oTable.fnGetNodes(aiNew[0]);
            console.log(nRow);
            editRow(oTable, nRow);
            nEditing = nRow;
            nNew = true;
        });

        table.on('click', '.delete', function (e) {
            e.preventDefault();

            if (confirm("Are you sure to delete this row ?") == false) {
                return;
            }

            var nRow = $(this).parents('tr')[0];
            oTable.fnDeleteRow(nRow);
        });

        table.on('click', '.cancel', function (e) {
            e.preventDefault();
            if (nNew) {
                currRowIndex--; // 取消 新建字段 将当前 序号 -1
                oTable.fnDeleteRow(nEditing);
                nEditing = null;
                nNew = false;
            } else {
                restoreRow(oTable, nEditing);
                nEditing = null;
            }
        });

        table.on('click', '.edit', function (e) {
            e.preventDefault();

            /* Get the row as a parent of the link that was clicked on */
            var nRow = $(this).parents('tr')[0];

            if (nEditing !== null && nEditing !== nRow) {
                // 当前有正在编辑的字段，但是不是当前选择的字段
                if (confirm('您有正在编辑的属性未填写，是否取消编辑？')) {
                    if (nNew) {
                        // 用户正在编辑 新建的 字段
                        // 当 选择的编辑的 行不是新建的 字段时，提醒用户是否要放弃新建而来修改已有的 字段
                        oTable.fnDeleteRow(nEditing); // cancel
                        nEditing = null;
                        nNew = false;
                        currRowIndex--;
                    } else {
                        // 用户正在编辑的 不是新建的字段
                        // 保存正在编辑的字段
                        saveRow(oTable, nEditing);
                        editOldRow(nRow);
                        nEditing = nRow;
                    }
                }
            } else if (nEditing === nRow) {
                // 保存 新建编辑
                // 输入域检查
                if (validate(nRow)) {
                    // 所有属性域 都已经 填写
                    saveRow(oTable, nEditing);
                    nEditing = null;
                    if (nNew) {
                        newBtn.click();// 紧接着 开始下一个新建编辑
                    }
                } else {
                    confirm('请填写完整所有属性再点击保存！！')
                }
            } else {
                // 开始新的 编辑
                editOldRow(nRow);
                nEditing = nRow;
            }
        });

        newBtn.click();
    };

    return {

        //main function to initiate the module
        init: function () {
            handleTable();
        }

    };

}();