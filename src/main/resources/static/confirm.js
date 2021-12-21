$(function () {
    $("form").validate({

        rules: {

            username: "required",

            email: {

                email: true
            },
            password: {

                required: true,

                minlength: 5
            },
            password_confirm: {
                equalTo: "#password"
            }
        },

        messages:
            {

            firstname: "Enter First Name",

            lastname: "Enter Last Name",

            password:
                {
                required: "Provide a password",

                minlength: "Password is to short"

            },
            password_confirm: {


                equalTo: "the password does not match the above"

            },

            email: "Enter email"

        },

        submitHandler: function (form)
        {

            form.submit();

        }
    });
});