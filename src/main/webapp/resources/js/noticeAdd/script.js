document.getElementById("buttonSubmit").addEventListener("click", function() {
   const formData = {
       memID: document.getElementById("memID").value,
       title: document.getElementById("title").value,
       content: document.getElementById("content").value,
       writer: document.getElementById("writer").value,
       indate: new Date().toISOString.split("T")[0],
   }
});

// index.jsp 파일에서 만든 메타CSRF태그두개를 js파일로 가져온다.
const csrfToken = document.querySelector("meta[name=_csrf]").getAttribute("content");
const csrfHeader = document.querySelector("meta[name=_csrf_header]").getAttribute("content");