/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function randomColor(){
    let r = parseInt(Math.random()*255);
    let g = parseInt(Math.random()*255);
    let b = parseInt(Math.random()*255);
    return `rgb(${r}, ${g}, ${b})`
}

function cateChart(id, cateLabels=[], cateInfors=[]){
    
    let colors=[]
    for (let i = 0; i < cateLabels.length; i++)
        colors.push(randomColor())
    
    const data = {
        labels: cateLabels,
        datasets: [{
                label: 'Thống kê sản phẩm theo danh mục',
                data: cateInfors,
                backgroundColor: colors,
                hoverOffset: 4
            }]
    };
    const config = {
        type: 'doughnut',
        data: data,
    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}

function prodChart(id, prodLabels=[], prodInfors=[]){
    let colors=[]
    for (let i = 0; i < prodLabels.length; i++)
        colors.push(randomColor())
    
    const data = {
        labels: prodLabels,
        datasets: [{
                label: 'Thống kê doanh thu sản phẩm',
                data: prodInfors,
                backgroundColor: colors,
                hoverOffset: 4,
                borderColor: 'rgb(75, 192, 192)'
            }]
    };
    const config = {
        type: 'line',
        data: data,
    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}

function prodMonthChart(id, prodLabels=[], prodInfors=[]){
    let colors=[]
    for (let i = 0; i < prodLabels.length; i++)
        colors.push(randomColor())
    
    const data = {
        labels: prodLabels,
        datasets: [{
                label: 'Thống kê doanh thu theo tháng',
                data: prodInfors,
                backgroundColor: colors,
                hoverOffset: 4
            }]
    };
    const config = {
        type: 'bar',
        data: data,
    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}