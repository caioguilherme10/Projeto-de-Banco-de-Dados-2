var config = {
    apiKey: "AIzaSyCeMk39RkNE1OuUxtLiS1GNO_Zs6hX_rJ4",
    authDomain: "bandod2-b3aa2.firebaseapp.com",
    databaseURL: "https://bandod2-b3aa2.firebaseio.com",
    projectId: "bandod2-b3aa2",
    storageBucket: "bandod2-b3aa2.appspot.com",
    messagingSenderId: "304746556105"
  };
  firebase.initializeApp(config);
// Pego os elementos do html email, senha e do butao logar.
const Email = document.getElementById('email');
const Password = document.getElementById('password');
const btnSignIn = document.getElementById('buttonSignIn');

// Funçao de login. quando ele de um click no butao login vai ativar essa funçao, que serve para logar ele no firebase.
btnSignIn.addEventListener('click', e => {
    
    // Pego os valores de email e pass.
    const email = Email.value;
    const pass = Password.value;
    
    let confirma = logar(email,pass);
});

function logar(email,pass){
		
    const auth = firebase.auth();
    
    //o metodo signInWithEmailAndPassword serve para fazer o login no firebase.
    auth.signInWithEmailAndPassword(email, pass).then(function(){

        localStorage.setObject("usu", []);

        let user = {
            email: Email.value
        };

        let array = localStorage.getObject("usu");
        array.push(user);
        localStorage.setObject("usu", array);

        window.location.replace("TelaPrincipal.jsp");

    }, function(error) {
        // Handle Errors here.
        if (email == ""||pass == "") {
            alert("Preencha os dados vazios!")
        }else{
            alert("Dados incorretos!")
        }
        // ...
    });
}

firebase.auth().onAuthStateChanged(firebaseUser =>{
    if(firebaseUser){
        
        //Se ele tiver logado com sucesso ele e mandado para a pagina principal.
        //home/caio/NetBeansProjects/BancoDeDados/src/main/java/com/mycompany/bancodedados/Servelt/TelaPrincipal.java
        window.location.replace("TelaPrincipal.jsp");
    }else{
    
    }
});