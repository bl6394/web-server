var bV = parseInt(navigator.appVersion);
if (document.images) {
    arImLoad = new Array("/images/minus.gif");
    arImList = new Array();
    for (counter in arImLoad) {
        arImList[counter] = new Image();
        arImList[counter].src = arImLoad[counter];
    }
}

isExpanded = false;

function reDo() {
    window.location.reload()
}

function getIndex(el) {
    ind = null;
    for (i = 0; i < document.layers.length; i++) {
        whichEl = document.layers[i];
        if (whichEl.id == el) {
            ind = i;
            break;
        }
    }
    return ind;
}

function arrange() {
    nextY = document.layers[firstInd].pageY + document.layers[firstInd].document.height - 10;
    for (i = firstInd + 1; i < document.layers.length; i++) {
        whichEl = document.layers[i];
        if (whichEl.visibility != "hide") {
            whichEl.pageY = nextY;
            nextY += whichEl.document.height - 10;
        }
    }
}

function expandIt(el) {
    whichEl = eval(el + "Child");
    whichIm = event.srcElement;
    if (whichEl.style.display == "none" || whichEl.isInited === undefined) {
        whichEl.style.display = "block";
        whichEl.isExpanded = true;
        whichIm.src = "/images/minus.gif";
        whichEl.isInited = true;
    }
    else {
        whichEl.style.display = "none";
        whichEl.isExpanded = false;
        whichIm.src = "/images/plus.gif";

    }
}

function showAll() {
    for (i = firstInd; i < document.layers.length; i++) {
        whichEl = document.layers[i];
        whichEl.visibility = "show";
    }
}

function expandAll(isBot) {
    newSrc = (isExpanded) ? "/images/minus.gif" : "/images/plus.gif";
        divColl =  document.getElementsByName("DIV");
        for (i = 0; i < divColl.length; i++) {
            if (divColl(i).className == "child") {
                divColl(i).style.display = (isExpanded) ? "none" : "block";
                divColl(i).isExpanded = (isExpanded) ? false : true;
            }
        }
        imColl = document.images.item("imEx");
        for (i = 0; i < imColl.length; i++) {
            imColl(i).src = newSrc;
        }
    isExpanded = !isExpanded;
}

with (document) {
    write("<STYLE TYPE='text/css'>");
    write(".parent {");
    ;
        write("margin-top:-10;")
    ;
    write("margin-bottom:15;");
    write("color:#000");
    write("}");
    write(".child {");
    ;
        write("display:none;")
    ;
    write("margin-left:10;");
    write("margin-top:-10;");
    write("}");
    write(".other {");
    ;
    write("}");
    write("</STYLE>");
}

function linkIt(whichEl, whichHref) {
    lay = eval(whichEl + "Child");
    if (!lay.isExpanded) expandIt(whichEl);
    nlay = document.images[whichHref];
    nlay.scrollIntoView();
    scroll(0, document.body.scrollTop - 20);
    nlay.parentElement.style.backgroundColor = "#FF99FF";
    setTimeout("nlay.parentElement.style.backgroundColor='white'", 1000);

}

function toggleOnAllCheckboxes(source) {
    checkboxes = document.getElementsByName('field');
    for(var i=0, n=checkboxes.length;i<n;i++) {
        checkboxes[i].checked = true;
    }
}

function toggleOffAllCheckboxes(source) {
    checkboxes = document.getElementsByName('field');
    for(var i=0, n=checkboxes.length;i<n;i++) {
        checkboxes[i].checked = false;
    }
}

function displayConstraints(source) {
    var constraintsJson=localStorage.getItem('CONSTRAINTS');
    if (!constraintsJson) {
        constraintsJson = '{}';
    }
    alert("Constraints: " + constraintsJson );
}


