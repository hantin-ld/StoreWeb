/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



function addComment(productId){
    fetch("/StoreWebApp/api/add-comment", {
        method: 'post',
        body: JSON.stringify({
            "content": document.getElementById("commentId").value,
            "productId": productId 
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res){
        console.info(res)
        
        return res.json()
    }).then(function(data){
        console.info(data)
        
        let ar = document.getElementById("commentArea")
        ar.innerHTML = `
        <div class="row">
            <div class="col-md-2" style="padding: 5px">
                <img class="rounded-circle img-fluid"  src="${data.user.avatar}"/>
            </div>
            <div class="col-md-10 my-date">
                <p>${data.content}</p>
                <i>${moment(data.createdDate).fromNow()}</i>
            </div>
        </div>
        ` + ar.innerHTML
    
        //location.reload()
    })
}

function addToCart(id, name , price){
    
    
    fetch("/StoreWebApp/api/cart", {
        method: 'post',
        body: JSON.stringify({
            "productId": id,
            "productName": name,
            "price": price,
            "quantity": 1
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res) {
        return res.json()
    }).then(function(data) {
        let cter = document.getElementById("cartCounter");
        cter.innerText = data
    })
}

function updateQuatityCart(obj, id){
    fetch("/StoreWebApp/api/cart", {
        method: "put",
        body: JSON.stringify({
            "productId": id,
            "productName": "",
            "price": 0,
            "quantity": obj.value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json()
    }).then(function(data){
        let cter = document.getElementById("cartCounter")
        cter.innerText = data.counter
        let cAmount = document.getElementById("cartAmount")
        cAmount.innerText = data.amount
        
           
    })
}

function deleteFromCart(productId){
    if(confirm("Bạn chắc muốn xóa sản phẩm này không?") == true){
        fetch(`/StoreWebApp/api/cart/${productId}`,{
            method: "delete"
        }).then(function(res){
            return res.json()
        }).then(function(data){
            let cter = document.getElementById("cartCounter")
            cter.innerText = data.counter
            
            let cAmount = document.getElementById("cartAmount")
            cAmount.innerText = data.amount
            location.reload()
            //let row = document.getElementById(`product${productId}`)
            //row.style.display = "none"
        })
    }
}

function pay(){
    if(confirm("Bạn có chắc thực hiện thanh toán ???") == true){
        fetch("/StoreWebApp/api/pay",{
            method: "post"
        }).then(function(res){
            res.json();
        }).then(function(code){
            console.info(code);
            location.reload();
        })
    }
}