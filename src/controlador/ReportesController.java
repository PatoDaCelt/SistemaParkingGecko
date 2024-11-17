package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import javax.swing.JOptionPane;

/* @author patmi */
public class ReportesController {

         //Método para crear el ticket
         public void TicketRetiro( int idVehiculo ) {

                  Document documento = new Document();

                  try {

                           String ruta = System.getProperty("user.home") + "/Escritorio";
                           PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Parking_Ticket.pdf"));
                           Image header = Image.getInstance("src/img/GeckoLogo.png");
                           header.scaleToFit(100, 100);
                           header.setAlignment(Chunk.ALIGN_CENTER);

                           try {
                                    Connection cn = Conexion.conectar();
                                    PreparedStatement pst = cn.prepareStatement("select * from tb_vehiculo where id_vehiculo = '" + "';");
                                    ResultSet rs = pst.executeQuery();

                                    if ( rs.next() ) {
                                             Paragraph parrafo = new Paragraph();
                                             parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                                             parrafo.add("Sistema Parking Gecko \n © Patricio Milán \n\n");
                                             parrafo.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.DARK_GRAY));
                                             parrafo.add("______________________________________________________________\n\n");
                                             Timestamp timestamp = rs.getTimestamp("hora_salida");
                                             Date fecha = new Date(timestamp.getTime());
                                             parrafo.add("Ticket de Retiro de " + rs.getString("tipo_vehiculo") + " - ( Placa: " + rs.getString("placa") + " )\n\n");
                                             parrafo.add(fecha + "\n");
                                             parrafo.add("______________________________________________________________\n\n");

                                             Paragraph contenido = new Paragraph();
                                             contenido.setAlignment(Paragraph.ALIGN_CENTER);
                                             contenido.add("Propietario: " + rs.getString("propietario") + "\n");

                                             Timestamp timestampEntrada = rs.getTimestamp("hora_entrada");
                                             Time horaEntrada = new Time(timestampEntrada.getTime());
                                             contenido.add("Salida: " + horaEntrada + "\n");

                                             Timestamp timestampSalida = rs.getTimestamp("hora_salida");
                                             Time horaSalida = new Time(timestampSalida.getTime());
                                             contenido.add("Salida: " + horaSalida + "\n");

                                             contenido.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.RED));
                                             contenido.add("\nValor Pagado: $ " + rs.getString("valor_pagado") + "MXN\n");
                                             contenido.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.DARK_GRAY));
                                             contenido.add("______________________________________________________________\n\n");

                                             documento.open();

                                             //Agregar datos
                                             documento.add(header);
                                             documento.add(parrafo);
                                             documento.add(contenido);
                                    }

                           } catch ( SQLException e ) {
                                    System.out.println("ERROR 1 EN: " + e);
                           }
                           documento.close();
                           JOptionPane.showMessageDialog(null, "Ticket Creado");

                  } catch ( DocumentException e ) {
                           System.out.println("ERROR 2 EN: " + e);
                  } catch ( FileNotFoundException ex ) {
                           System.out.println("ERROR 3 EN: " + ex);
                  } catch ( IOException ex ) {
                           System.out.println("ERROR 4 EN: " + ex);
                  }

         }

}
