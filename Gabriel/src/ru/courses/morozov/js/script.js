$(document).ready(function () {
    var sliders = $(".slider");
    var inputs = $(".main-table td:nth-child(6) input");
    var maxValue = 190;
    var minValue = 0;
    var step = 10;
    var stepSwitchValue = 100;
    var sliderCoefficient = 10;

    sliders.each(function (i) {
        $(this).slider({
            min: minValue,
            max: maxValue,
            step: step,
            slide: function (e, ui) {
                var sliderValue = ui.value;
                inputs.eq(i).val(sliderValue);
                if (sliderValue > stepSwitchValue) {
                    var newValue = (ui.value - stepSwitchValue) * sliderCoefficient + stepSwitchValue;
                    inputs.eq(i).val(newValue);
                }
            }
        })
    });

    inputs.each(function (i) {
        $(this).change(function () {
            var inputValue = inputs.eq(i).val();
            var valueCoefficient = 10;
            var maxInputValue = 1000;
            if (inputValue % valueCoefficient !== 0) {
                inputs.eq(i).val(inputValue - (inputValue % valueCoefficient));
            }
            if (inputValue > maxInputValue) {
                inputs.eq(i).val(maxInputValue);
            }
            if (inputValue > stepSwitchValue) {
                var newValue = (inputValue - stepSwitchValue) / sliderCoefficient + stepSwitchValue;
                sliders.eq(i).slider("value", newValue);
                return;
            }
            if (inputValue === "") {
                inputs.eq(i).val("0");
            }
            sliders.eq(i).slider("value", inputValue);
        }).keypress(function (e) {
            var chr = String.fromCharCode(e.which);
            if (chr < '0' || chr > '9') {
                return false;
            }
        });
    });

    $(".main-table .scroll-pane").jScrollPane({
        verticalDragMaxHeight: 27
    });

    $(".table-previous-application .scroll-pane").jScrollPane({
        verticalDragMaxHeight: 14
    });
});
