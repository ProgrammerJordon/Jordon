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

let elInputUsername = document.querySelector('#username');


let elFailureMessage = document.querySelector('.failure-message');
let elSuccessMessage = document.querySelector('.success-message');


let elPassword = document.querySelector('#password');
let elPassword2 = document.querySelector('#password-retype');
let elMissMatchMessage = document.querySelector('.mismatch-message');


function isMoreThan4Length(value) {
    // 아이디가 4글자 이상이면

    return value.length >= 4;
    // 4글자 이상을 내포하는 밸류를 반환한다
}


elInputUsername.onkeyup = function () {
    if (isMoreThan4Length(elInputUsername.value)) {
        //입력된 아이디 키가 4글자 이상이면

        elSuccessMessage.classList.remove('hide');
        //성공 메시지가 보여야 함

        elFailureMessage.classList.add('hide');
        //실패 메시지가 가려져야 함
    } else {
        elSuccessMessage.classList.add('hide');
        //성공 메시지가 가려져야 함

        elFailureMessage.classList.remove('hide');
        //실패 메시지가 보여야 함
    }
}

function isMatch(password1, password2) {
    // password1과 password2가 같다면

    if (password1 === password2)

        return true;

    else {

        return false;
    }
    // password1 = password2 의 값을 반환
}

elPassword2.onkeyup = function () {
    if (isMatch(elPassword.value, elPassword2.value)) {
        //비밀번호가 일치하면

        elMissMatchMessage.classList.add('hide');
        //메시지 안 보여야 함


    } else {
        elMissMatchMessage.classList.remove('hide');
        // 불일치 메시지 보여야 함


    }
}

