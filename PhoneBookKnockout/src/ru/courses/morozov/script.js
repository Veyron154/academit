$(document).ready(function () {
    var vm = new PhoneBookViewModel();
    ko.applyBindings(vm);

});

function PhoneBookViewModel() {
    var self = this;
    self.tableItems = ko.observableArray();
    self.name = ko.observable();
    self.surname = ko.observable();
    self.phone = ko.observable();
    self.isTopChecked = ko.observable(false);
    self.isChecked = ko.computed(function () {
        return self.isTopChecked() === true;
    });

    self.addTableItem = function () {
        var inputs = $(".input-phone-book");
        var isCorrect = true;

        inputs.each(function () {
            if ($(this).val() == "") {
                $(this).parents(".form-inline").addClass("has-error");
                isCorrect = false;
            } else {
                $(this).parents(".form-inline").removeClass("has-error");
            }
        });

        if (!isCorrect) {
            $.alert({
                title: "Ошибка заполнения",
                content: "Заполните выделенные поля",
                confirmButton: "OK"
            });
            return;
        }
        self.tableItems.push(new TableItemsViewModel(self.name, self.surname, self.phone, self));
        renumberRow();
        resetTextFields();
        self.name = "";
        self.surname = "";
        self.phone = "";
    };
}

function TableItemsViewModel(name, surname, phone, parent) {
    var self = this;

    self.itemName = name;
    self.itemSurname = surname;
    self.itemPhone = phone;

    self.removeTableItem = function () {
        var rows = $(".table-phone-book tr:has(td [type='checkbox']:checked):visible");
        var messageString = "следующие контакты? <br />";
        /*$(".table-phone-book tr:has(td [type='checkbox']:checked) .third-column").each(function () {
         messageString += $(this).text() + "<br />";
         });*/
        if (rows.length === 0) {
            rows = this;
            messageString = "контакт: " + self.itemSurname() + " ?";
        }

        $.confirm({
            title: "Подтверждение удаления",
            content: "Вы действительно хотите удалить " + messageString,
            confirmButton: "OK",
            cancelButton: "Отмена",
            confirm: function () {
                parent.tableItems.remove(rows);
                renumberRow();
            }
        });
    }
}

var resetTextFields = function () {
    var inputs = $(".input-phone-book");
    inputs.each(function () {
        $(this).parents(".form-group").removeClass("has-error");
        $(this).val("");
    });
};

var renumberRow = function () {
    var rowsIndices = $(".table-phone-book tbody .second-column");
    rowsIndices.each(function (i) {
        $(this).text(i + 1);
    });
};




