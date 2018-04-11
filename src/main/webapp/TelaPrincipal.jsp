<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
	<!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="asset/css/materialize.min.css">
        
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://www.gstatic.com/firebasejs/4.12.1/firebase.js"></script>

	<!--Let browser know website is optimized for mobile-->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8"/>
    </head>
    <body class="purple lighten-5">
        <!--Import jQuery before materialize.js-->
        <div class="navbar-fixed">
            <nav>
                <div class="nav-wrapper purple darken-4">
                    <ul class="right">
                        <li><a id="nome"></a></li>
                        <li><a id="email"></a></li>
                        <li><a id="buttonLogout">Sair</a></li>
                    </ul>
                </div>
            </nav>
	</div>
        <div class="row">
            <nav>
                <div class="nav-wrapper">
                    <form>
                        <div class="input-field purple darken-4 col s10">
                            <input id="search" type="search" required>
                            <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                            <i class="material-icons">close</i>
                        </div>
                        <div class="purple lighten-5 col s2">
                            <button class="btn waves-effect waves-light purple darken-4" type="submit" name="action">Procurar</button>
                        </div>
                    </form>
                </div>
            </nav>
        </div>
        <div class="row">
            <div class="col s10">
                <!-- Dropdown Trigger -->
                <a class='dropdown-trigger btn purple darken-4' href='#' data-target='dropdown1'>Selecionar Categoria</a>
                
                <!-- Dropdown Structure -->
                <ul id='dropdown1' class='dropdown-content'>
                    <li><a href="front?action=Programacao" class="purple-text text-darken-4">Programação</a></li>
                    <li><a href="front?action=Frontend" class="purple-text text-darken-4">Front-end</a></li>
                    <li><a href="front?action=Mobile" class="purple-text text-darken-4">Mobile</a></li>
                    <li><a href="front?action=Infraestrutura" class="purple-text text-darken-4">Infraestrutura</a></li>
                    <li><a href="front?action=Design" class="purple-text text-darken-4">Design & UX</a></li>
                    <li><a href="front?action=Business" class="purple-text text-darken-4">Business</a></li>
                    <li><a href="front?action=Assuntos" class="purple-text text-darken-4">Assuntos Gerais</a></li>
                </ul>
            </div>
            <div class="purple lighten-5 col s2">
                <a class="waves-effect waves-light btn modal-trigger purple darken-4" href="#modal1">Novo Topico</a>
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <div class="container">
                    <ul class="collection">
                        <li class="collection-item col s12">
                            <div class="row">
                                <div class="col s4">
                                    Topico
                                </div>
                                <div class="col s4">
                                    Categoria
                                </div>
                                <div class="col s4">
                                    Autor
                                </div>
                            </div>
                        </li>
                        <c:forEach  var="topico" items="${Topicos}">
                            <li class="collection-item col s12">
                                <div class="row">
                                    <div class="col s4">
                                        ${topico.titulo}
                                    </div>
                                    <div class="col s4">
                                        ${topico.categoria}
                                    </div>
                                    <div class="col s2">
                                        ${topico.nome}
                                    </div>
                                    <form class="col s2" action="front" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="action" value="IrParaTopico">
                                        <input type="hidden" name="id" value="${topico.id}">
                                        <input class="button" type="submit" value="Ir">
                                    </form>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div id="modal1" class="modal">
            <div class="modal-content">
                <form class="col s12" action="front" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="titulo" id="first_name2" type="text" class="validate">
                            <label class="active" for="first_name2">Titulo</label>
                        </div>
                        <div class="input-field col s6">
                            <select name="categoria">
                                <option value="" disabled selected>Categoria</option>
                                <option value="Programacao">Programação</option>
                                <option value="Frontend">Front-end</option>
                                <option value="Mobile">Mobile</option>
                                <option value="Infraestrutura">Infraestrutura</option>
                                <option value="Design UX">Design & UX</option>
                                <option value="Business">Business</option>
                                <option value="Assuntos Gerais">Assuntos Gerais</option>
                            </select>
                            <label>Categoria</label>
                        </div>
                        <div class="input-field col s12">
                            <textarea name="conteudo" id="textarea1" class="materialize-textarea"></textarea>
                            <label for="textarea1">Textarea</label>
                        </div>
                    </div>
                    <div id="usuario">
                        
                    </div>
                    <input type="hidden" name="action" value="SalvarTopico"/>
                    <div class="row">
                        <button class="btn purple darken-4 waves-effect waves-light">Confimar</button>
                    </div>
                </form>
            </div>
        </div>
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <!-- Compiled and minified JavaScript -->
  	<script src="asset/js/materialize.min.js"></script>
        <script>
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
	</script>
        <script type="text/javascript" src="asset/js/localStoragePlus.js"></script>
        <script>
            // Or with jQuery
            $(document).ready(function(){
               
               $('select').formSelect();
                
            });
            $(document).ready(function(){
                $('.modal').modal();
            });
            $(document).ready(function(){
                
                $('.dropdown-trigger').dropdown();
            });
            firebase.auth().onAuthStateChanged(firebaseUser =>{
		if(firebaseUser){
                    console.log("ta on");
                }else{
                    console.log("ta off");
                    localStorage.setObject("usu", []);
                    window.location.replace("Login.html");
                }
            });
            // Pegando o elemento do butao sair.
            const btnLogout = document.getElementById('buttonLogout');
            
            // A funÃ§ao para deslogar o usuario do firebase. com o evento de click e a funcao signOut.
            btnLogout.addEventListener('click', e => {
    
                firebase.auth().signOut();
                localStorage.setObject("usu", []);
                //O usuario e mandado para a pagina inicial.
                window.location.replace("Login.html");
            });
            
            let array = localStorage.getObject("usu");
            
            const dbRefObjec = firebase.database().ref();
            
            const two = dbRefObjec.child('users').orderByChild('email').equalTo(array[0].email);

            two.on('child_added', snap => {

                document.getElementById("nome").innerHTML = snap.val().nome;
                document.getElementById("email").innerHTML = array[0].email;
                
                usuario.innerHTML = "<input type='hidden' name='email1' id='email1' type='text' value='"+ snap.val().email + "'/>" +
                                    "<input type='hidden' name='nome1' id='nome1' type='text' value='"+ snap.val().nome +"'/>";
                
                let user = {
                    nome: snap.val().nome,
                    email: snap.val().email
                };

                array = [];
                array.push(user);
                localStorage.setObject("usu", array);
                
            });
        </script>
    </body>
</html>