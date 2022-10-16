function sidemenu() {
    alert('javascript');
}

function checkAll() {
    if($("#cboxAll").is(':checked')) {
        $("input[name=cbox]").prop("checked", true);
    } else {
        $("input[name=cbox]").prop("checked", false);
    }
}

$(document).on("click", "input:checkbox[name=cbox]", function(e) {
    let chks = document.getElementsByName("cbox");
    let chksChecked = 0;
    for(let i=0; i<chks.length; i++) {
        let cbox = chks[i];
        if(cbox.checked) {
            chksChecked++;
        }
    }
    if(chks.length == chksChecked){
        $("#cboxAll").prop("checked", true);
    }else{
        $("#cboxAll").prop("checked",false);
    }
});