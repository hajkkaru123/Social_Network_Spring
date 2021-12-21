const delAccount = document.getElementById('delAccount')
const delPosts = document.getElementById('delPosts')

$(delAccount).click(() => {
    if (confirm("Do you want to delete acc")) {
        alert("Account has been Deleted ")
        return true;
    } else {
        alert("cancelled")
        return false;
    }
})

$(delPosts).click(() => {
    if (confirm("Delete all posts?")) {
        alert("Posts has been Deleted ")
        return true;
    } else {
        alert("cancelled post delete")
        return false;
    }
})