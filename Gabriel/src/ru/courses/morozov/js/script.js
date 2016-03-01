$(document).ready(function () {
    var sliders = $(".slider");
    var inputs = $(".main-table td:nth-child(6) input");
    var maxValue = 1000;
    var minValue = 0;
    var minStep = 10;

    sliders.each(function (i) {
        $(this).slider({
            min: minValue,
            max: maxValue,
            step: minStep,
            slide: function () {
                var sliderValue = sliders.eq(i).slider("value");
                inputs.eq(i).val(sliderValue);
                var stepSwitchValue = 100;
                if (sliderValue >= stepSwitchValue) {
                    var maxStep = 100;
                    sliders.eq(i).slider("option", "step", maxStep);
                }
            },
            stop: function () {
                var sliderValue = sliders.eq(i).slider("value");
                inputs.eq(i).val(sliderValue);
                if (sliderValue === minValue) {
                    sliders.eq(i).slider("option", "step", minStep);
                }
            }
        })
    });

    inputs.each(function (i) {
        $(this).change(function () {
            var inputValue = inputs.eq(i).val();
            var valueCoefficient = 10;
            if (inputValue % valueCoefficient !== 0) {
                inputs.eq(i).val(inputValue - (inputValue % valueCoefficient));
            }
            if (inputValue > maxValue) {
                inputs.eq(i).val(maxValue);
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
