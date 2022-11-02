// 캔버스 세팅
let canvas;
let ctx;
canvas = document.getElementById("game_canvas")
ctx = canvas.getContext("2d")

canvas.width=400;
canvas.height=700;
document.body.append(canvas);

let backgroundImage,spaceshipImage,bulletImage,enemyImage,gameoverImage;
let gameOver = false;
let score = 0;
// 우주선 좌표
let spaceshipX = canvas.width/2-32
let spaceshipY = canvas.height-64

let bulletList = []; // 총알리스트 저장하는 리스트
function Bullet() {
    this.x=0;
    this.y=0;
    this.init=function () {
        this.x=spaceshipX; // +20 해서 좌표를 조정해도됨 총알좌표
        this.y=spaceshipY;
        this.alive = true; //true 면 살아있는 총알 false면 사라진 총알
        bulletList.push(this);
    }
    this.update = function() {
        this.y -= 5;
    };

    this.checkHit = function() {
        for(let i = 0; i < enemyList.length; i++) {
            if(
                this.y <= enemyList[i].y &&
                this.x >= enemyList[i].x &&
                this.x <= enemyList[i].x+80) {
                score += 10;
                this.alive = false;
                enemyList.splice(i, 1);
                if(bulletList[i].y = canvas.height) {
                    bulletList.splice(i,1);
                }
            }
        }
    };
}

function generateRandomValue(min, max) {
    let randomNum = (Math.random()*(max-min+1))+min;
    return randomNum
}

let enemyList = []
function Enemy() {
    this.x=0;
    this.y=0;
    this.init = function () {
        this.y = 0;
        this.x = generateRandomValue(0, canvas.width-64);
        enemyList.push(this)
    }
    this.update = function () {
        this.y += 2; // enemy가 떨어지는 속도 조절

        if(this.y >= canvas.height-48) {
            gameOver = true;

        }
    }
}

function loadImage() {
    backgroundImage = new Image();
    backgroundImage.src="/static/images/background.gif";

    spaceshipImage = new Image();
    spaceshipImage.src="/static/images/spaceship.png";

    bulletImage = new Image();
    bulletImage.src="/static/images/bullet.png";

    enemyImage = new Image();
    enemyImage.src="/static/images/enemy.png"

    gameoverImage = new Image();
    gameoverImage.src="/static/images/gameover.jpeg"

}

let keyDown={}
function setupKeyboardListener() {
    document.addEventListener("keydown",function (event) {
        keyDown[event.keyCode] = true;
    });
    document.addEventListener("keyup", function (event){
        delete keyDown[event.keyCode];

        if(event.keyCode == 32) {
            createBullet(); // 총알생성
        }
    });
}

function createBullet() {
    let b = new Bullet();
    b.init();
}

function createEnemy() {
    const interval = setInterval(function (){
        let e = new Enemy();
        e.init();
    },500)
}

function update() {
    if(39 in keyDown) {
        spaceshipX += 5;
    }
    if(37 in keyDown) {
        spaceshipX -= 5;
    }

    if(spaceshipX <= 0) {
        spaceshipX = 0;
    }
    if(spaceshipX >= canvas.width-64) {
        spaceshipX = canvas.width-64;
    }

    // 총알 움직이게 하기위한 y좌표 변환
    for(let i=0; i<bulletList.length; i++) {
        if(bulletList[i].alive) {
            bulletList[i].update();
            bulletList[i].checkHit();
        }
    }

    for(let i=0; i<enemyList.length; i++) {
        enemyList[i].update();
    }


}

function render() {
    ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);
    ctx.drawImage(spaceshipImage, spaceshipX, spaceshipY);
    ctx.fillText(`SCORE:${score}`, 20, 20);
    ctx.fillStyle = "White";
    ctx.font = "20px Arial"

    for(let i = 0; i<bulletList.length; i++) {
        if(bulletList[i].alive) {
            ctx.drawImage(bulletImage, bulletList[i].x, bulletList[i].y);
        }
    }

    for(let i=0; i<enemyList.length; i++) {
        ctx.drawImage(enemyImage, enemyList[i].x, enemyList[i].y);
    }
}

function main() {
    if(!gameOver) {
        update();
        render();
        requestAnimationFrame(main);
    }else {
        ctx.drawImage(gameoverImage, 10, 100, 400, 400);
    }

}

loadImage();
setupKeyboardListener();
createEnemy();
main();