package Controlador;

public class Reporte {

    private String encabezado = "";
    private String pie = "\n </body>\n" +
                         "</html>";

    public String reporteUsuarios(){
        String reporte="";

        crearEncabezado("Reporte de usuarios");
        reporte = reporte + encabezado;

        String [] encabezados = {"ID del usuario", "Nombre", "Apellido", "Nombre de usuario", "Rol", "Bibliografias prestadas"};
        String[][] datosUsuarios = Usuario.datosUsuario();
        String[][] datosReporteFormateados = new String[datosUsuarios.length][6];

        for (int i = 0; i < datosUsuarios.length; i++) {
            datosReporteFormateados[i][0]=datosUsuarios[i][0];
            datosReporteFormateados[i][1]=datosUsuarios[i][1];
            datosReporteFormateados[i][2]=datosUsuarios[i][2];
            datosReporteFormateados[i][3]=datosUsuarios[i][3];
            String numeroRol =datosUsuarios[i][4];

            if (numeroRol.equals("0")) {
                datosReporteFormateados[i][4]="Administrador";
            }else if (numeroRol.equals("1")) {
                datosReporteFormateados[i][4]="Estudiante";
            }else if (numeroRol.equals("2")) {
                datosReporteFormateados[i][4]="Catedratico";
            }

            //Contar bibliografias
            String bibliografiasPrestadas = "0";
            String[][] datosPrestamoPorUsuario = Prestamo.listarPrestamoNoDevueltos(datosUsuarios[i][0]);

            if (datosPrestamoPorUsuario != null) {
                if (datosPrestamoPorUsuario[0][0] != null) {
                    bibliografiasPrestadas = datosPrestamoPorUsuario.length +"";
                }
            }
            datosReporteFormateados[i][5]=bibliografiasPrestadas;
        }

        String tabla = crearTabla(encabezados,datosReporteFormateados);

        reporte =reporte+tabla+pie;

        return reporte;
    }

    public String reportePrestamos(){
        String reporte="";

        crearEncabezado("Reporte de prestamos");
        reporte = reporte + encabezado;

        String tabla ="";

        String [] encabezados = {"ID del prestamo","ID del usuario", "Titulo", "Tipo", "Fecha", "Devuelto"};

        String[][] datosPrestamo = Prestamo.datosPrestamo();
        String[][] datosFormateados = new String[datosPrestamo.length][6];

        //Del mas reciente al mas antiguo
        int contadorAux = 0;
        for (int i = datosFormateados.length-1; i >= 0; i--) {
            datosFormateados[contadorAux][0]=datosPrestamo[i][0];
            datosFormateados[contadorAux][1]=datosPrestamo[i][4];
            datosFormateados[contadorAux][2]=datosPrestamo[i][1];

            String numeroTipo = Bibliografia.buscarBibliografia(datosPrestamo[i][1])[0];

            if (numeroTipo.equals("0")) {
                datosFormateados[i][3]="Libro";
            }else if (numeroTipo.equals("1")) {
                datosFormateados[i][3]="Revista";
            }else{
                datosFormateados[i][3]="Tesis";
            }

            datosFormateados[contadorAux][4]=datosPrestamo[i][2];

            String devuelto = "No";
            if (datosPrestamo[i][3].equals("1")) {
                devuelto="Si";
            }
            datosFormateados[contadorAux][5]=devuelto;

            contadorAux++;
        }

        tabla= crearTabla(encabezados,datosFormateados);

        reporte =reporte+tabla+pie;
        return reporte;
    }

    public String reporteBibliografias() {

        String reporte="";

        crearEncabezado("Reporte de usuarios");
        reporte = reporte + encabezado;

        String [] encabezados = {"Tema","Bibliografias asociadas"};
        String[][] datosBibliografia = Bibliografia.datosBibiliografia();

        //Temas disponibles/registrados

        String temasSeparadosPorComas = "";
        for (int i = 0; i < datosBibliografia.length; i++) {
            String[] temasBibliografiaIndividual = datosBibliografia[i][5].split(",");

            for (int j = 0; j < temasBibliografiaIndividual.length; j++) {

                String temaIndividual = temasBibliografiaIndividual[j];

                if (i == 0 && j ==0) {
                    temasSeparadosPorComas+= temaIndividual + " ;";
                }else{
                    int verificaciones = 1;
                    String [] temasVerificados = temasSeparadosPorComas.split(";");
                    boolean noAparecio =true;
                    for (int k = 0; k < temasVerificados.length; k++) {

                        if (temasVerificados[k].trim().equals(temaIndividual.trim())) {
                            noAparecio = false;
                        }
                        //Si ya reviso todo y no aparecio
                        if (verificaciones == temasVerificados.length && noAparecio) {
                            temasSeparadosPorComas+= " " + temaIndividual + " ;";
                        }

                        verificaciones++;
                    }
                }
            }
        }

        //quita el ultimo caracter que es un ;
        temasSeparadosPorComas = temasSeparadosPorComas.substring(0, temasSeparadosPorComas.length()-1);

        String [] temas = temasSeparadosPorComas.split(";");
        String[][] datosReporteFormateados = new String[temas.length][2];

        for (int i = 0; i < datosReporteFormateados.length; i++) {
            datosReporteFormateados[i][0] =temas[i].trim();

            int bibliografiasRegistradas = 0;
            //Contar temas
            for (int j = 0; j < datosBibliografia.length; j++) {
                String[] temasBibliografiaIndividual = datosBibliografia[j][5].split(",");
                for (int k = 0; k < temasBibliografiaIndividual.length; k++) {
                    if (temasBibliografiaIndividual[k].trim().equals(temas[i].trim())) {
                        bibliografiasRegistradas++;

                    }
                }
            }


            datosReporteFormateados[i][1] =bibliografiasRegistradas+"";

        }

        String tabla = crearTabla(encabezados,datosReporteFormateados);

        reporte =reporte+tabla+pie;
        return reporte;

    }

        private String crearTabla(String [] encabezados, String[][] datosCuerpo){
        String tabla ="\n<table border='1'>";


        //Encabezados
        String encabezadoTabla = "\n\t<thead>\n\t\t<tr>";
        for (String encabezado:encabezados) {
            encabezadoTabla = encabezadoTabla + "\n\t\t\t<th>"+encabezado+"</th>";
        }
        encabezadoTabla=encabezadoTabla+"\n\t\t</tr>\n\t</thead>";

        //cuerpo de tabla
        String cuerpoTabla = "\n\t<tbody>";
        for (int i = 0; i < datosCuerpo.length; i++) {
            String fila = "\n\t\t<tr>";
            for (int j = 0; j < datosCuerpo[i].length; j++) {
                fila=fila+"\n\t\t\t<td>"+datosCuerpo[i][j]+"</td>";
            }
            fila=fila+"\n\t\t</tr>";
            cuerpoTabla=cuerpoTabla+fila;
        }
        cuerpoTabla=cuerpoTabla+"\n\t</tbody>";

        tabla=tabla+encabezadoTabla+cuerpoTabla+"\n</table>";

        return tabla;
    }

    private void crearEncabezado (String titulo){
        encabezado =    "<!DOCTYPE html>\n" +
                        "<html lang=\"es\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>"+titulo+"</title>\n" +
                        "</head>\n" +
                        "<body>";
    }

}
