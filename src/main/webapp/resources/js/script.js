// DOM 객체 연결(html혹은jsp 파일안에 있는 태그들 자바스크립트와 연결시키는 과정)

const container = document.getElementById('container');
const menuAdmin = document.getElementById('menuAdmin');
const menuList = document.getElementById('menuList');

// CSRF 토큰과 헤더이름 가져오기
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');


// 데이터를 조회할때 사용할 기능을 정의해야합니다.
function fetchMenus() {
    fetch('/menu/all').then(response => response.json())
        .then(menus => {
            menuList.innerHTML = '';    // 기존메뉴목록을 초기화
            menus.forEach(menu => {
                // 각 메뉴 아이템을 생성해서 리스트에 추가
                const menuItem = document.createElement('div');
                menuItem.className = 'menu-item';
                menuItem.innerHTML = `
                <a href= "#" class="menu-link" style="text-decoration:none; color:black;">
                    <h3>${menu.title}</h3>
                    <p>${menu.content}</p>
                    <small>작성자:${menu.writer}, 작성일:${menu.indate}, 조회수:${menu.count}</small>
                </a>
                <br>
                <br>    
                `
                // 게시글을 메인페이지에서 하나씩 클릭할때
                menuItem.querySelector(".menu-link").addEventListener('click', (event) => {
                    event.preventDefault();
                    console.log(`event:${event}`);

                    incrementCount(menu.idx).then(() => window.location.href=`/noticeCheckPage?idx=${menu.idx}`)
                });
                    menuList.appendChild(menuItem);
            })
        })
}

function incrementCount(idx) {
    return fetch(`/menu/count/${idx}`,{
        method: 'PUT',
        headers: {
            [csrfHeader]:csrfToken
        }
    }).then(response => {
        if (!response.ok) {
            console.log("데이터가 프론트서버에서 백엔드서버 잘 안넘어감")
        }
    }).catch(error => {
        console.log(`Error:${error}`);
    })
}

// 메인페이지가 열리면 자동 실행
window.addEventListener("load", fetchMenus);