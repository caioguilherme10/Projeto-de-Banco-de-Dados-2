<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
-->
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
        <div class="navbar-fixed">
            <nav>
                <div class="nav-wrapper purple darken-4">
                    <a href="TelaPrincipal.jsp" class="brand-logo">Tela Principal</a>
                    <ul class="right">
                        <li><a id="nome"></a></li>
                        <li><a id="email"></a></li>
                        <li><a id="buttonLogout">Sair</a></li>
                    </ul>
                </div>
            </nav>
	</div>
        <div class="row">
        <div class="container col s8">
            <div class="row">
                <div class="col s12">
                    <div class="card white">
                        <div class="card-content black-text">
                            <span class="card-title">titulo</span>
                            <p>Propriedade</p>
                        </div>
                        <div class="card-action">
                            <span class="card-title">Nome</span>
                            <a class="right">data</a>
                            <p>I am a very simple card. I am good at containing small bits of information.
                                I am convenient because I require little markup to use effectively.</p>
                        </div>
                    </div>
                </div>
            </div>
            <c:forEach  var="comentario" items="${Topico.comentario}">
                <div class="row">
                    <div class="col s12">
                        <div class="card white">
                            <div class="card-action">
                                <span class="card-title">${comentario.nome}</span>
                                <a class="right">${comentario.data}</a>
                                <p>${comentario.conteudo}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="row">
                <div>
                    <h4>Comentar</h4>
                </div>
                <form class="col s12" action="front" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <div class="input-field col s12">
                            <textarea id="textarea1" class="materialize-textarea"></textarea>
                            <label for="textarea1">Textarea</label>
                        </div>
                        <div class="row">
                            <button class="btn purple darken-4 waves-effect waves-light right">Confimar</button>
                        </div>
                    </div>
                    <input type="hidden" name="action" value="SalvarComentario"/>
                </form>
            </div>
        </div>
            <div class="container col s4">
                <div>
                    <h4>Topicos sugeridos</h4>
                </div>
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
            
            /*firebase.auth().onAuthStateChanged(firebaseUser =>{
		if(firebaseUser){
                    console.log("ta on");
                }else{
                    console.log("ta off");
                    localStorage.setObject("usu", []);
                    window.location.replace("Login.html");
                }
            });*/
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
            
            document.getElementById("nome").innerHTML = array[0].nome;
            document.getElementById("email").innerHTML = array[0].email;

        </script>
    </body>
</html>