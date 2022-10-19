let giMenuDuration = 700;

// 전체 메뉴를 오른쪽으로 슬라이드하여서 보여준다.
function ShowMenu(){
    $('.menu_bg' ).css( { 'display' : 'block' } );
    $('.menu' ).css( { 'left' : '-100%' } );
    $('.menu' ).animate( { left: '0px' }, { duration: giMenuDuration } );
}

// 전체 메뉴를 왼쪽으로 슬라이드하여서 닫는다.
function HideMenu(){
    $('.menu' ).animate( { left: '-100%' }, { duration: giMenuDuration, complete:function(){ $('.menu_bg' ).css( { 'display' : 'none' } ); } } );
}




// findo_signup_agreemnent
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

//////////////////

