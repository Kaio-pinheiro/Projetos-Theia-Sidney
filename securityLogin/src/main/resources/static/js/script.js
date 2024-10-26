const showPasswordCheckbox = document.querySelector('#showPassword');
const passwordField = document.querySelector('.senha');

showPasswordCheckbox.addEventListener('change', function () {
    // Alterna entre mostrar e ocultar a senha
    const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordField.setAttribute('type', type);
});