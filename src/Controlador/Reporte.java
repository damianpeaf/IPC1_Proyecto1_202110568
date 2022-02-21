package Controlador;

public class Reporte {

    private String encabezado = "";
    private String pie = "\n </body>\n" +
                         "</html>";

    public String reporteUsuarios(){
        String reporte="";

        crearEncabezado("Reporte de usuarios");
        reporte = reporte + encabezado;

        return reporte;
    }

    public String reportePrestamos(){
        String reporte="";

        crearEncabezado("Reporte de prestamos");
        reporte = reporte + encabezado;

        String tabla ="";

        int numeroCampos = 6;
        String [] encabezados = {"ID del prestamo","ID del usuario", "Titulo", "Tipo", "Fecha", "Devuelto"};

        String[][] datosPrestamo = Prestamo.datosPrestamo();
            String[][] datosFormateados = new String[datosPrestamo.length][6];

            for (int i = 0; i < datosFormateados.length; i++) {
                datosFormateados[i][0]=datosPrestamo[i][0];
                datosFormateados[i][1]=datosPrestamo[i][4];
                datosFormateados[i][2]=datosPrestamo[i][1];
                //tipo ? datosFormateados[i][3]=datosPrestamo[i][0];
                datosFormateados[i][3]="";
                datosFormateados[i][4]=datosPrestamo[i][2];

                String devuelto = "No";
                if (datosPrestamo[i][3].equals("1")) {
                    devuelto="Si";
                }
                datosFormateados[i][5]=devuelto;
            }

        tabla= crearTabla(encabezados,datosFormateados);

        reporte =reporte+tabla+pie;
        return reporte;
    }

    private String crearTabla(String [] encabezados, String[][] datosCuerpo){
        String tabla ="\n<table>";


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
