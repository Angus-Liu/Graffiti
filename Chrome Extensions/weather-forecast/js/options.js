var city = localStorage.city || '成都';
document.getElementById('city').value = city;
document.getElementById('save').onclick = () => {
    localStorage.city = document.getElementById('city').value;
    alert('保存成功！');
}
