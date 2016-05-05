$(document).ready(function () {
    var vm = new PhoneBookViewModel();
    ko.applyBindings(vm);

});

function PhoneBookViewModel() {
    var self = this;
    self.tableItems = ko.observableArray([]);
    self.name = ko.observable("");
    self.surname = ko.observable("");
    self.phone = ko.observable("");
    self.isTopChecked = ko.observable(false);
    self.filterText = ko.observable("");

    self.isTopChecked.subscribe(function (newValue) {
        for (var i = 0; i < self.tableItems().length; ++i) {
            self.tableItems()[i].isChecked(newValue);
        }
    });

    self.surnameError = ko.observable("");
    self.nameError = ko.observable("");
    self.phoneError = ko.observable("");

    var renumberRow = function () {
        for (var i = 0; i < self.tableItems().length; ++i) {
            self.tableItems()[i].index(i + 1);
        }
    };

    self.addTableItem = function () {
        var isCorrect = true;

        var checkCorrect = function (field, error) {
            if (field() === "") {
                error("has-error");
                isCorrect = false;
            } else {
                error("");
            }
        };

        checkCorrect(self.surname, self.surnameError);
        checkCorrect(self.name, self.nameError);
        checkCorrect(self.phone, self.phoneError);

        if (!isCorrect) {
            $.alert({
                title: "Ошибка заполнения",
                content: "Заполните выделенные поля",
                confirmButton: "OK"
            });
            return;
        }

        var isUniquePhone = true;
        for (var i = 0; i < self.tableItems().length; ++i) {
            if (self.tableItems()[i].itemPhone === self.phone()) {
                isUniquePhone = false;
            }
        }

        if (!isUniquePhone) {
            $.alert({
                title: "Ошибка заполнения",
                content: "Контакт с номером " + self.phone() + " уже существует",
                confirmButton: "OK"
            });
            return;
        }

        self.tableItems.push(new TableItemsViewModel(self.name(), self.surname(), self.phone()));
        renumberRow();

        self.name("");
        self.surname("");
        self.phone("");
    };

    self.removeTableItem = function (item) {
        var rows = [];
        for (var i = 0; i < self.tableItems().length; ++i) {
            if (self.tableItems()[i].isChecked() === true && self.tableItems()[i].showRow() === true) {
                rows.push(self.tableItems()[i]);
            }
        }
        var messageString = "следующие контакты? <br />";
        for (var j = 0; j < rows.length; ++j) {
            messageString += rows[j].itemSurname + "<br />";
        }
        if (rows.length === 0) {
            rows.push(item);
            messageString = "контакт: " + item.itemSurname + " ?";
        }
        $.confirm({
            title: "Подтверждение удаления",
            content: "Вы действительно хотите удалить " + messageString,
            confirmButton: "OK",
            cancelButton: "Отмена",
            confirm: function () {
                self.tableItems.removeAll(rows);
                renumberRow();
            }
        });
    };

    self.executeFilter = function () {
        var filter = self.filterText().toLowerCase();
        for (var i = 0; i < self.tableItems().length; ++i) {
            if (self.tableItems()[i].itemSurname.toLowerCase().indexOf(filter) === -1 &&
                self.tableItems()[i].itemName.toLowerCase().indexOf(filter) === -1 &&
                self.tableItems()[i].itemPhone.toLowerCase().indexOf(filter) === -1) {
                self.tableItems()[i].showRow(false);
            }
        }
    };

    self.cancelFilter = function () {
        for (var i = 0; i < self.tableItems().length; ++i) {
            self.tableItems()[i].showRow(true);
            self.filterText("");
        }
    }
}

function TableItemsViewModel(name, surname, phone) {
    var self = this;

    self.itemName = name;
    self.itemSurname = surname;
    self.itemPhone = phone;

    self.index = ko.observable();
    self.isChecked = ko.observable();
    self.showRow = ko.observable(true);
}






