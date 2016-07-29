
var nTableRows = 1;
var usingTextMode = false;
var modeTMP = "";

window.onbeforeunload = function() {
    var lang = document.documentElement.lang;
    if (nTableRows > 1) {
        if (lang === 'vn') {
            return "Bạn có một vài dữ liệu ở trên bảng (" + (nTableRows - 1) + "). Bạn có muốn bỏ trang này không?";
        } else if (lang === 'cz') {
            return "Máte vyplněná data v tabulce (" + (nTableRows - 1) + "). Chcete opravdu opustit stránku?";
        } else /*if (lang === 'en') */ {
            return "You have some data left on the table (" + (nTableRows - 1) + "). Do you want to leave this page?";
        }
    }
};
;
function move(lang) {
    if (lang === 'vn') {
        window.location.href = "/EnterpriseApps/vn/LabelMaker";
    } else if (lang === 'cz') {
        window.location.href = "/EnterpriseApps/cz/LabelMaker";
    } else /*if (lang === 'en') */ {
        window.location.href = "/EnterpriseApps/en/LabelMaker";
    }
}
;
function moveBack() {
    window.location.href = "/EnterpriseApps/";
}
;
function toggleTextEditMode() {

    var addbtn = document.getElementById("ID_addToListButton");
    var removebtn = document.getElementById("ID_removeLastRowButton");
    var textmodebtn = document.getElementById("ID_plainTextModeButton");
    var datatable = document.getElementById("ID_table");
    var textarea = document.getElementById("ID_textArea");
    var tablesection = document.getElementById("ID_textAreaTableSection");
    var formsection = document.getElementById("ID_formSection");
    var formtable = document.getElementById("ID_formTable");
    var pdfwindow = document.getElementById("ID_pdfWindow");
    var dropzone = document.getElementById("ID_dropzone");
    var leftslideout = document.getElementById("ID_stylechoosersection");
    var innerslideout = document.getElementById("ID_innerstylechooser");
    if (usingTextMode === false) {
        var r = true;
        if (nTableRows > 1) {
            if (document.documentElement.lang === "vn") {
                r = confirm("Nếu bạn mở chế độ văn bản, bảng mặt hàng đang có sẽ bị xoá! Bạn có muốn làm vậy không?");
            } else if (document.documentElement.lang === "cz") {
                r = confirm("Tabluka zboží bude smazána! Jste si jistý?");
            } else /*if (document.documentElement.lang === "en")*/ {
                r = confirm("Going to Plain Text Mode will delete the table you've created! Are you sure?");
            }
        }
        if (r === true) {
            pdfwindow.src = "about:blank";
            usingTextMode = true;
            for (var i = nTableRows; i > 1; i--) {
                datatable.deleteRow(-1);
            }
            nTableRows = 1;
            textarea.value = "";

            tablesection.style.height = "290px";
            formsection.style.height = "50px";
            leftslideout.style.top = "175px";
            innerslideout.style.top = "0px";

            textarea.style.height = "240px";
            setTimeout(function() {
                pdfwindow.style.height = "390px";
                dropzone.style.opacity = "0.5";
            }, 1000);

            formtable.style.display = "none";
            datatable.style.display = "none";

            formtable.style.opacity = "0";
            datatable.style.opacity = "0";

            addbtn.style.opacity = "0";
            removebtn.style.opacity = "0";
            textarea.style.opacity = "1";

            addbtn.style.display = "none";
            removebtn.style.display = "none";

            //textmodebtn.style.backgroundColor = "#ff0000";
            modeTMP = document.getElementById("ID_plainTextModeButton").value;
            textmodebtn.value = "\u2190";

        }
    } else {
        pdfwindow.src = "about:blank";
        usingTextMode = false;
        textarea.value = "";

        pdfwindow.style.height = "110px";
        dropzone.style.opacity = "0";

        setTimeout(function() {
            tablesection.style.height = "290px";
            formsection.style.height = "330px";
            leftslideout.style.top = "453px";
            innerslideout.style.top = "0px";

            textarea.style.height = "0px";

            setTimeout(function() {
                formtable.style.display = "table";
                datatable.style.display = "table";
                setTimeout(function() {
                    formtable.style.opacity = "1";
                    datatable.style.opacity = "1";
                }, 500);
            }, 500);

            addbtn.style.opacity = "1";
            removebtn.style.opacity = "1";
            textarea.style.opacity = "1";

            addbtn.style.display = "inline-block";
            removebtn.style.display = "inline-block";

            //textmodebtn.style.backgroundColor = "#599bb3"; //will ruin btn effects   
            textmodebtn.value = modeTMP;
            modeTMP = document.getElementById("ID_plainTextModeButton").value;

        }, 1000);
    }

}
;
function addToList() {
    if (nTableRows < 169) {
        var textAreaTableSection = document.getElementById("ID_textAreaTableSection");
        if (nTableRows === 7) {
            textAreaTableSection.style.height = "397px";
            document.getElementById('ID_scroll2AddButton').click();
        }
        var title = document.getElementById('ID_titleInput').value.trim();
        var price = document.getElementById('ID_price').value.trim();
        var amount = document.getElementById('ID_amount').value.trim();
        if (!isValidTitle(title)) {
            //alert("Please enter a title for your item");
            document.getElementById('ID_titleInput').style.backgroundColor = '#ff0000';
        } else if (!(isNumericString(price) && isValidFloatPrice(price))) {
            //alert("Please enter a valid price for your item");
            document.getElementById('ID_price').style.backgroundColor = '#ff0000';
        } else if (!isNumericString(amount)) {
            //alert("Please enter an amount for your item");
            document.getElementById('ID_amount').style.backgroundColor = '#ff0000';
        } else {
            var ean = "-";
            var curr = $('input[name=CURR]:checked').val();
            var unit = $('input[name=UNIT]:checked').val();
            var sc = ";";
            if (title !== "" && price !== "" && amount !== "") {
                var line = ean + sc + title + sc + price + sc + curr + sc + amount + sc + unit + sc + "\n";
                var textArea = document.getElementById('ID_textArea');
                textArea.value += line;
            }
            if (curr === "Kc") {
                curr = "Kč";
            }

            var targetTable = document.getElementById("ID_table");
            var row = targetTable.insertRow(-1);

            var cellNu = row.insertCell(0);
            var cellEan = row.insertCell(1);
            var cellTi = row.insertCell(2);
            var cellPr = row.insertCell(3);
            var cellCu = row.insertCell(4);
            var cellAm = row.insertCell(5);
            var cellUn = row.insertCell(6);
            cellNu.innerHTML = nTableRows;
            cellEan.innerHTML = ean;
            cellTi.innerHTML = title;
            cellAm.innerHTML = amount;
            cellUn.innerHTML = unit;
            cellPr.innerHTML = price;
            cellCu.innerHTML = curr;
            nTableRows++;

            textAreaTableSection.scrollTop = textAreaTableSection.scrollHeight;
        }
    } else {
        var lang = document.documentElement.lang;
        var alert = "";
        if (lang === "vn") {
            alert = "You can only have 168 items per table (4 A4 pages)";
        } else if (lang === "cs") {
            alert = "Nelze mít víc než 168 zboží v tabulce.";
        } else /*if (lang === "cs")*/ {
            alert = "You can only have 168 items per table (4 A4 pages)";
        }
        alert(alert);
    }
}
;
function deleteLastRow() {
    if (nTableRows > 1) {
        document.getElementById("ID_table").deleteRow(-1);
        var textArea = document.getElementById('ID_textArea');
        var lastNL = textArea.value.lastIndexOf('\n');
        var tmp = textArea.value.substring(0, lastNL);
        var lastlastNL = tmp.lastIndexOf('\n');

        textArea.value = tmp.substring(0, lastlastNL) + '\n';
        nTableRows--;
        if (nTableRows === 7) {
            var section = document.getElementById("ID_textAreaTableSection");
            section.style.height = "290px";
        }
    }
}
;
function isOkToSubmit(tt) {
    if (usingTextMode) {
        if (tt.length === 0) {
            return false;
        }
        var lines = tt.split('\n');
        var lineCount = 0;
        for (var i = 0; i < lines.length; i++) {
            lineCount = i + 1;
            if (lines[i] !== "") {
                var cntSemicolumn = 0;
                for (var j = 0; j < lines[i].length; j++) {
                    if (lines[i].charAt(j) === ";") {
                        cntSemicolumn++;
                    }
                }
                if (cntSemicolumn < 6) {
                    var lang = document.documentElement.lang;
                    if (lines[i].length > 50) {
                        lines[i] = lines[i].substring(0, 50) + "...";
                    }
                    if (lang === "vn") {
                        alert("Lỗi ở dòng thứ " + lineCount + ". Bạn hãy sửa lỗi và lặp lại\n"
                                + "Lỗi: \"" + lines[i] + "\"");
                    } else if (lang === "cz") {
                        alert("Chyba: Řádek " + lineCount + ". Opravte chybu a opakujte akci\n"
                                + "Chyba: \"" + lines[i] + "\"");
                    } else /*if(lang==="en")*/ {
                        alert("Error at line " + lineCount + ". Correct the mistake and repeat\n"
                                + "Error: \"" + lines[i] + "\"");
                    }
                    return false;
                }
            }
        }
        return true;
    } else {
        if (nTableRows > 1) {
            return true;
        }
        return false;
    }
}
;
function isValidTitle(t) {
    if (t.length === 0) {
        return false;
    }
    return true;
}
;
function hasOneDecPoints(str) {
    var nDecPoints = 0;
    for (var i = 0; i < str.length; i++) {
        if (str.charAt(i) === '.') {
            nDecPoints++;
            if (nDecPoints > 1) {
                return false;
            }
        }
    }
    return nDecPoints <= 1;
}
;
function isDigitChar(chx) {
    var numbers = ".0123456789";
    for (var i = 0; i < numbers.length; i++) {
        if (chx === numbers.charAt(i)) {
            return true;
        }
    }
    return false;
}
;
function isNumericString(str) {
    if (str.length === 0) {
        return false;
    } else if (str.charAt(0) === '.' || str.charAt(str.length - 1) === '.') {
        return false;
    } else if ((str.charAt(0) === '0' && str.charAt(1) !== '.') || isZero(str) || !hasOneDecPoints(str)) {
        return false;
    } else {
        for (var i = 0; i < str.length; i++) {
            if (!isDigitChar(str.charAt(i))) {
                return false;
            }
        }
    }
    return true;
}
;
function isValidFloatPrice(str) {
    var s = str.substring(str.indexOf('.') + 1, str.length);
    if (s.length === 2) {
        return true;
    }
    return false;
}
;
function isZero(str) {
    for (var i = 0; i < str.length; i++) {
        if (str.charAt(i) !== '0' && str.charAt(i) !== '.') {
            return false;
        }
    }
    return true;
}
;
function previewPDF() {
    var t = document.getElementById('ID_textArea').value;
    if (!isOkToSubmit(t)) {
        if (usingTextMode) {
            document.getElementById('ID_textArea').style.backgroundColor = "#FFD699";
        }
    } else {
        document.getElementById('ID_dataToSubmit').value = t;

        document.getElementById('ID_scroll2PDFWindow').click();
        document.getElementById('ID_pdfWindow').style.height = "900px";

        setTimeout(function() {
            document.FForm.submit();
        }, 800);
    }
}
;
function clearError() {
    document.getElementById('ID_titleInput').style.backgroundColor = 'white';
    document.getElementById('ID_price').style.backgroundColor = 'white';
    document.getElementById('ID_amount').style.backgroundColor = 'white';
    document.getElementById('ID_textArea').style.backgroundColor = 'white';
}
;
function downloadText() {
    var text = document.getElementById('ID_textArea').value;
    if (text === "") {
        //alert("No data to download yet");
    } else {
        var pom = document.createElement('a');
        pom.setAttribute('href', 'data:x-application/text;charset=utf-8,' + encodeURIComponent(text.replace(/\r?\n/g, "\r\n")));
        pom.setAttribute('download', 'items.txt');
        pom.click();
    }
}
;
$(function() {

    /**
     * smooth scrolling
     * @returns {undefined}
     */
    $('a[href*=#]:not([href=#])').click(function() {
        if (location.pathname.replace(/^\//, '') === this.pathname.replace(/^\//, '') && location.hostname === this.hostname) {

            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top
                }, 1000);
                return false;
            }
        }
    });
});
;
function GetClock() {
    d = new Date();
    nday = d.getDay();
    nmonth = d.getMonth();
    ndate = d.getDate();
    nyear = d.getYear();
    nhour = d.getHours();
    nmin = d.getMinutes();
    if (nyear < 1000)
        nyear = nyear + 1900;

    if (nmin <= 9) {
        nmin = "0" + nmin;
    }

    document.getElementById('clockbox').innerHTML =
            nhour + ":" + nmin + "<br/>" + ndate + " . " + (nmonth + 1) + " . " + nyear;
    setTimeout("GetClock()", 1000);
}
window.onload = GetClock;

;
function removeDiv(div) {
    div.style.opacity = "0";
    setTimeout(function() {
        div.parentNode.removeChild(div);
        parent.document.getElementById('ID_pdfWindow').style.height = "555px";
    }, 500);
}
;
function refillValueOf(input) {
    var p = input.value;
    var correctValue = '';
    if (p.indexOf('.') === -1) {
        if (p.length > 2) {
            correctValue = p.substring(0, p.length - 2) + "." + p.substr(p.length - 2, 2);
        } else if (p.length === 0) {

        } else {
            correctValue = "0." + p;
        }
        input.value = correctValue;
    }
}
;
function initDragDrop() {
    function endsWith(str, suffix) {
        return str.indexOf(suffix, str.length - suffix.length) !== -1;
    }
    var dropzone = document.getElementById('ID_dropzone');

    dropzone.ondrop = function(e) {
        this.className = 'dropzone';
        e.stopPropagation();
        e.preventDefault();

        var formData = new FormData();
        var xhr = new XMLHttpRequest();
        var isValidToUpload = true;
        var reason = "";
        if (e.dataTransfer.files.length > 1) {
            isValidToUpload = false;
            if (document.documentElement.lang === "vn") {
                reason = "Chỉ cho phép tải lên 1 tập tin";
            } else if (document.documentElement.lang === "cz") {
                reason = "Je dovoleno pouze 1 soubor";
            } else /*if (document.documentElement.lang === "en") */ {
                reason = "Only 1 file allowed";
            }
        } else {
            for (var i = 0; i < e.dataTransfer.files.length; i++) {
                var name = e.dataTransfer.files[i].name;
                var size = Math.round(e.dataTransfer.files[i].size / 1024);
                if (size > 1024) {
                    isValidToUpload = false;
                    if (document.documentElement.lang === "vn") {
                        reason = "Tập tin quá lớn (max 1024 kB)";
                    } else if (document.documentElement.lang === "cz") {
                        reason = "Soubor je příliš velký (max 1024 kB)";
                    } else /*if (document.documentElement.lang === "en") */ {
                        reason = "File is too big (max 1024 kB)";
                    }
                    reason += "\n\n\"" + name + "\" (" + size + " kB)";
                    break;
                } else if (!endsWith(name, ".txt")) {
                    isValidToUpload = false;
                    if (document.documentElement.lang === "vn") {
                        reason = "Tập tin không hợp lệ (phải là .txt)";
                    } else if (document.documentElement.lang === "cz") {
                        reason = "Neplatný soubor (musí být .txt)";
                    } else /*if (document.documentElement.lang === "en") */ {
                        reason = "Not a valid file extension (must be .txt)";
                    }
                    reason += "\n\n\"" + name + "\"";
                    break;
                } else {
                    formData.append('file', e.dataTransfer.files[i]);
                    isValidToUpload = true;
                }
            }
        }

        xhr.onload = function() {
            var data = this.responseText;
            document.getElementById('ID_textArea').value = data;
        };
        if (isValidToUpload) {
            xhr.open("post", "/EnterpriseApps/Upload");
            xhr.send(formData);
        } else {
            document.getElementById('ID_textArea').value = reason;
        }

    };
    dropzone.ondragover = function() {
        this.className = 'dropzone dragover';
        dropzone.style.opacity = "1";
        return false;
    };

    dropzone.ondragleave = function() {
        this.className = 'dropzone';
        dropzone.style.opacity = "0.5";
        return false;
    };

}
;
function showStyleChooser() {
    var chooser = document.getElementById("ID_stylechoosersection");
    if (chooser.style.left === "0px") {
        chooser.style.left = "-580px";
    } else {
        chooser.style.left = "0px";
    }
}
;
function changeStyleImage(styleName) {
    var styleImage = document.getElementById("ID_styleImageFrame");
    var styleImageDiv = document.getElementById("ID_styleImageDiv");
    styleImageDiv.style.opacity = "0";
    setTimeout(function() {
        switch (styleName) {
            case "style1":
                styleImage.src = "/EnterpriseApps/images/style1.png";
                break;
            case "style2":
                styleImage.src = "/EnterpriseApps/images/style2.png";
                break;
            case "style3":
                styleImage.src = "/EnterpriseApps/images/style3.png";
                break;
            case "style4":
                styleImage.src = "/EnterpriseApps/images/style4.png";
                break;
            case "style5":
                styleImage.src = "/EnterpriseApps/images/style5.png";
                break;
            case "style6":
                styleImage.src = "/EnterpriseApps/images/style6.png";
                break;
            default:
        }

        styleImageDiv.style.opacity = "1";
    }, 200);
}
;
function changeInputMaxLengthOf(length) {

    var nameInput = document.getElementById("ID_titleInput");
    if (length === 22) {
        if (nameInput.value.length > 22) {
            nameInput.value = nameInput.value.substring(0, 22);
        }
        nameInput.maxLength = "22";
    } else {
        nameInput.maxLength = "" + length;
    }
}
;