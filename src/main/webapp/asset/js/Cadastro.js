// Initialize Firebase
var config = {
    apiKey: "AIzaSyCeMk39RkNE1OuUxtLiS1GNO_Zs6hX_rJ4",
    authDomain: "bandod2-b3aa2.firebaseapp.com",
    databaseURL: "https://bandod2-b3aa2.firebaseio.com",
    projectId: "bandod2-b3aa2",
    storageBucket: "",
    messagingSenderId: "304746556105"
};
firebase.initializeApp(config);

const novoEmail = document.getElementById('email');
const novoPass = document.getElementById('password');
const novoName = document.getElementById('first_name');
const btnNovo = document.getElementById('btnNovo');


btnNovo.addEventListener('click' , e => {
		
    if(novoEmail.value == "" || novoPass.value == "" || novoName.value == ""){
        swal("Preencha os campos vazios!")
    }else{
        firebase.auth().createUserWithEmailAndPassword(novoEmail.value, novoPass.value).catch(function(error) {
            swal("Erro ao Cadastrar!")
        });
    }
});

localStorage.setObject("usu", []);

firebase.auth().onAuthStateChanged(firebaseUser =>{

    if(firebaseUser.uid){

        let user = {
            email: novoEmail.value,
            nome : novoName.value
        };

        let array = localStorage.getObject("usu");
        array.push(user);
        localStorage.setObject("usu", array);

        firebase.database().ref('users/' + firebaseUser.uid).set({
            nome : novoName.value,
            email : novoEmail.value,
            senha : novoPass.value
        });

        swal("Feito com sucesso")
        
    }

});