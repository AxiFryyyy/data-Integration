function showConfirmationDialog(courseId) {
    const confirmationDialog = document.createElement("div");
    confirmationDialog.innerHTML = `
        <div class="confirmation-dialog-mask"></div>
        <div class="confirmation-dialog">
            <h3>Confirm to delete this course?</h3>
            <div class="buttons">
                <button class="btn btn-success" onclick="deleteCourse(${courseId})">OK</button>
                <button class="btn btn-secondary" onclick="hideConfirmationDialog()">Cancel</button>
            </div>
        </div>
    `;
    document.body.appendChild(confirmationDialog);
}
function showConfirmationChooseDialog(studentId,courseId) {
    const confirmationDialog = document.createElement("div");
    confirmationDialog.innerHTML = `
        <div class="confirmation-dialog-mask"></div>
        <div class="confirmation-dialog">
            <h3>Confirm to choose this course?</h3>
            <div class="buttons">
                <button class="btn btn-success" onclick="chooseCourse(${studentId}, ${courseId})">OK</button>
                <button class="btn btn-secondary" onclick="hideConfirmationDialog()">Cancel</button>
            </div>
        </div>
    `;
    document.body.appendChild(confirmationDialog);
}

function hideConfirmationDialog() {
    const confirmationDialog = document.querySelector(".confirmation-dialog");
    if (confirmationDialog) {
        document.body.removeChild(confirmationDialog.parentNode);
    }
}
function chooseCourse(studentId, courseId) {
    fetch(`/chooseCourse/choose/${courseId}`, {method: "POST", body: JSON.stringify(studentId)})
        .then(response => {
            console.log(studentId);
            if (response.ok) {
                location.reload();
            } else {
                console.error("Failed to choose course.");
            }
        });
    hideConfirmationDialog();
}
function deleteCourse(courseId) {
    fetch(`/myCourses/delete/${courseId}`, {method: "POST", body: new FormData(document.getElementById(`delete-form-${courseId}`))})
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                console.error("Failed to delete course.");
            }
        });
    hideConfirmationDialog();
}
