String.format = function () {
    var string = arguments[0];
    var fromIndex = 1;
    while (fromIndex < arguments.length) {
        string = string.replace(new RegExp('\\%s', 'gm'), arguments[fromIndex]);
        fromIndex++;
    }
    return string;
};

String.formatAsCSharp = function () {
    var string = arguments[0];
    var fromIndex = 1;
    while (fromIndex < arguments.length) {
        string = string.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[fromIndex]);
        fromIndex++;
    }
    return string;
};