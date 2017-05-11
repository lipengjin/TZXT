var FormEditable = function () {

    var initEditables = function () {

        //global settings
        $.fn.editable.defaults.inputclass = 'form-control';
        $.fn.editable.defaults.url = '/post';

        var ledgerId = $('input#ledgerId').val();

        //editables element samples 
        $('#ledgerName').editable({
            url: '/ledger/updateLedgerName/' + ledgerId,
            type: 'text',
            pk: 1,
            name: 'ledgerName',
            title: 'LedgerName',
            validate: function (value) {
                if ($.trim(value) === '') return '该字段不能为空';
            }
        });

        $('#ledgerComment').editable({
            url: '/ledger/updateLedgerComment/' + ledgerId,
            type: 'text',
            pk: 1,
            name: 'ledgerComment',
            title: 'LedgerComment',
            validate: function (value) {
                if ($.trim(value) === '') return '该字段不能为空';
            }
        });

    };

    return {
        //main function to initiate the module
        init: function () {

            // init editable elements
            initEditables();

        }

    };

}();