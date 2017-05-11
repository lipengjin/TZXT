var LedgerDataManage = function () {

    var initLedgerDatas = function () {

        var createLedgerBtn = $('#createLedgerBtn');
        var pullDataBtn = $('#pullDataBtn');

        var createLedgerSuccessNote = jQuery('#createLedgerSuccessNote');
        var createLedgerErrorNote = jQuery('#createLedgerErrorNote');
        var pullDataInfoNote = jQuery('#pullDataInfoNote');
        var pullDataErrorNote = jQuery('#pullDataErrorNote');

        var ledgerId = $('input#ledgerId').val();

        createLedgerBtn.click(function (e) {
            e.preventDefault();

            $.ajax({
                url: '/db/createLedger?ledgerId=' + ledgerId,
                type: 'get',
                success: function (data, status) {
                    if (data) {
                        createLedgerSuccessNote.show();
                    } else {
                        createLedgerErrorNote.show();
                    }
                },
                error: function (error) {
                    console.log(error);
                    createLedgerErrorNote.show();
                }
            });

        });

        pullDataBtn.click(function (e) {
            e.preventDefault();

            $.ajax({
                url: '/db/pullData?ledgerId=' + ledgerId,
                type: 'get',
                success: function (data, status) {
                    if (data) {
                        pullDataInfoNote.show();
                    } else {
                        pullDataErrorNote.show();
                    }
                },
                error: function (error) {
                    console.log(error.responseJSON.message);
                    pullDataErrorNote.show();
                }
            });

        });

    };

    return {
        //main function to initiate the module
        init: function () {

            // init editable elements
            initLedgerDatas();

        }

    };

}();