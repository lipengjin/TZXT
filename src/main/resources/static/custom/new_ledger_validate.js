var NewLedgerValidate = function () {

    var handleValidate = function () {

        var newLedgerForm = $('#new_ledger_form');
        console.log(newLedgerForm);
        newLedgerForm.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                'ledger.name': {
                    required: true
                },
                'ledger.sourceTable': {
                    required: true
                },
                'ledger.comment': {
                    required: false
                },
                fieldNameTmp: {
                    required: true
                },
                fieldTypeTmp: {
                    required: true
                }
            },

            messages: { // custom messages for radio buttons and checkboxes
                'ledger.name': {
                    required: 'Ledger Name can\'t be NULL'
                },
                'ledger.sourceTable': {
                    required: 'Source Table Name can\'t be NULL'
                },
                fieldNameTmp: {
                    required: 'Field Name can\'t be NULL'
                },
                fieldTypeTmp: {
                    required: 'Field Type can\'t be NULL'
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit

            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            submitHandler: function (form) {
                form.submit();
            }
        });

        newLedgerForm.find('input').keypress(function (e) {
            if (e.which === 13) {
                if (newLedgerForm.validate().form()) {
                    newLedgerForm.submit();
                }
                return false;
            }
        });

    };

    return {
        //main function to initiate the module
        init: function () {

            handleValidate();

        }

    };

}();