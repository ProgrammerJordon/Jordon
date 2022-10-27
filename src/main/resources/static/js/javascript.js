let giMenuDuration = 700;

// 전체 메뉴를 오른쪽으로 슬라이드하여서 보여준다.
function ShowMenu() {
    $('.menu_bg').css({'display': 'block'});
    $('.menu').css({'left': '-100%'});
    $('.menu').animate({left: '0px'}, {duration: giMenuDuration});
}

// 전체 메뉴를 왼쪽으로 슬라이드하여서 닫는다.
function HideMenu() {
    $('.menu').animate({left: '-100%'}, {
        duration: giMenuDuration, complete: function () {
            $('.menu_bg').css({'display': 'none'});
        }
    });
}


// findo_signup_agreemnent
function checkAll() {
    if ($("#cboxAll").is(':checked')) {
        $("input[name=cbox]").prop("checked", true);
    } else {
        $("input[name=cbox]").prop("checked", false);
    }
}

$(document).on("click", "input:checkbox[name=cbox]", function (e) {
    let chks = document.getElementsByName("cbox");
    let chksChecked = 0;
    for (let i = 0; i < chks.length; i++) {
        let cbox = chks[i];
        if (cbox.checked) {
            chksChecked++;
        }
    }
    if (chks.length == chksChecked) {
        $("#cboxAll").prop("checked", true);
    } else {
        $("#cboxAll").prop("checked", false);
    }
});

////////////////////////////////

function check_pw(){

    var pw = document.getElementById('memberpassword').value;
    var SC = ["!","@","#","$","%"];
    var check_SC = 0;

    if(pw.length < 8 || pw.length>16){
        window.alert('비밀번호는 8글자 이상, 20글자 이하만 이용 가능합니다.');
        document.getElementById('memberpassword').value='';
    }
    for(var i=0;i<SC.length;i++){
        if(pw.indexOf(SC[i]) != -1){
            check_SC = 1;
        }
    }
    if(check_SC == 0){
        window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
        document.getElementById('memberpassword').value='';
    }
    if(document.getElementById('memberpassword').value !='' && document.getElementById('memberpassword2').value!=''){
        if(document.getElementById('memberpassword').value==document.getElementById('memberpassword2').value){
            document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
            document.getElementById('check').style.color='#5fbb91';
        }
        else{
            document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
            document.getElementById('check').style.color='red';
        }
    }
}

////////////////////////////////////////////////////////////////////////
function CheckInputType(obj) {
    let str = obj.value;
    let len = str.length;
    let ch = str.charAt(0);
    for(i=0; i<len; i++){
        ch = str.charAt(i);
        if((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
            continue;
        }
        else {
            alert("영문과 숫자만 입력 가능합니다.");
            obj.value="";
            obj.focus();
            return false;
        }
    }
    return true;
}
////////////////////////////////////////////////////
function onlyNumber(obj) {
    obj.value = obj.value.replace(/[^0-9]/g, "");
}
///////////////////////////////////////////////////////
$("#membername").keyup(function (event) { regexp = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
    v = $(this).val();
    if (regexp.test(v)) {alert("실명을 입력하여 주세요\n한글만 입력가능 합니다.");
    $(this).val(v.replace(regexp, ''));} });
////////////////////////////////////////////////////////////////////////
function idcheck_confirm() {

    $('.input_id').focusout(function(){
        let memberid = $('.input_id').val(); // input_id에 입력되는 값

        $.ajax({
            url : "http://localhost:7777/signup_form",
            type : "post",
            data : {memberid: memberid},
            dataType : "json",
            success : function(idCheck){
                if(idCheck == 0){
                    $("#checkId").html('사용할 수 없는 아이디입니다.');
                    $("#checkId").attr('color','red');
                } else{
                    $("#checkId").html('사용할 수 있는 아이디입니다.');
                    $("#checkId").attr('color','green');
                }
            },
            error : function(){
                alert("서버요청실패");
            }
        })
    })
}
////////////////////////////
//board.js
function check(){
    if($.trim($("#writer").val())==""){
        alert("글쓴이를 입력하세요!");
        $("#writer").val("").focus();
        return false;
    }
    if($.trim($("#title").val())==""){
        alert("글제목을 입력하세요!");
        $("#title").val("").focus();
        return false;
    }
    if($.trim($("#content").val())==""){
        alert("글내용을 입력하세요!");
        $("#content").val("").focus();
        return false;
    }
}

// announcement board

