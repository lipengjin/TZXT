var LedgerManage = function () {

    var ledgerManage = function () {

        var table = $('#ledgerManage')

        table.on("click", ".delete", function (e) {
            e.preventDefault();

            if (confirm("你确定要删除此台账表?") == false) {
                return;
            }
            var id = $('>td', $(this).parents('tr')[0])[0].textContent;

            var _this = this;
            var changeStatus = function () {
                console.log($('a', $(_this).parents('tr')[0])[1]);
                $('a', $(this).parents('tr')[0])[1].disable = true;
            };

            $.ajax({
                url: '/ledger/tableNotExist/' + id,
                type: 'get',
                success: function (data, status) {
                    if (data) {
                        if (confirm("此台账表尚未创建实体表，或者表中没有数据，可以放心删除，是否删除？") == false) {
                            return;
                        }
                    } else {
                        if (confirm("此台账表已经有数据，请谨慎删除，是否删除？") == false) {
                            return;
                        }
                    }
                    $.ajax({
                        url: '/ledger/delete/' + id,
                        type: 'delete',
                        success : function (data, status) {
                            if (data) {
                                changeStatus();
                            }
                        },
                        error : function () {
                            console.log(error.responseJSON.message);
                            confirm(err.responseJSON.message);
                        }
                    });
                },
                error: function (error) {
                    console.log(error.responseJSON.message);
                    confirm(err.responseJSON.message);
                }
            });

            console.log(id)
        })
    };

    return {
        init: function () {
            // init ledger manage
            ledgerManage();
        }
    }
}();

