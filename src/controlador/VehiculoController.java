package controlador;

import conexion.Conexion;
import modelo.Vehiculo;
import java.sql.*;
import java.util.ArrayList;

/* @author patmi */
public class VehiculoController {

         //Método para registrar el ingreso de un vehiculo
         public boolean guardar( Vehiculo objeto ) {
                  boolean respuesta = false;
                  Connection cn = Conexion.conectar();

                  try {
                           PreparedStatement consulta = cn.prepareStatement("insert into tb_vehiculo values (?, ?, ?, ?, ?, ?, ?, ?)");
                           consulta.setInt(1, 0); //Id
                           consulta.setString(2, objeto.getPlaca());
                           consulta.setString(3, objeto.getPropietario());
                           consulta.setString(4, objeto.getTipoVehiculo());
                           consulta.setString(5, objeto.getHoraEntrada());
                           consulta.setString(6, objeto.getHoraSalida());
                           consulta.setDouble(7, objeto.getValorPagado());
                           consulta.setString(8, objeto.getEstado());

                           if ( consulta.executeUpdate() > 0 ) {
                                    respuesta = true;
                           }

                  } catch ( SQLException e ) {
                           System.out.println("ERROR AL REGISTRAR INGRESO DE VEHICULO" + e);
                  }
                  return respuesta;
         }

         //Método para consultar el vehiculo a buscar
         public ArrayList<Vehiculo> listaVehiculo = new ArrayList();

         public ArrayList buscarVehiculosPlacaFecha( String placaPropietario, String fecha ) {
                  String sql = "";

                  fecha = fecha.replace("/", "-");
                  if ( !placaPropietario.isEmpty() && !fecha.isEmpty() ) {
                           sql = "select * from tb_vehiculo where placa LIKE '%" + placaPropietario + "%' OR propietario LIKE '%" + placaPropietario + "%' AND hora_entrada LIKE '" + fecha + "%';";
                  } else if ( !placaPropietario.isEmpty() ) {
                           sql = sql = "select * from tb_vehiculo where placa LIKE '%" + placaPropietario + "%' OR propietario LIKE '%" + placaPropietario + "%';";
                  } else if ( !fecha.isEmpty() ) {
                           sql = "select * from tb_vehiculo where hora_entrada LIKE '" + fecha + "%';";
                  }

                  Statement st;

                  try {
                           Connection cn = Conexion.conectar();
                           st = cn.createStatement();
                           ResultSet rs = st.executeQuery(sql);

                           Vehiculo vehiculo;
                           while ( rs.next() ) {
                                    vehiculo = new Vehiculo();
                                    vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
                                    vehiculo.setPlaca(rs.getString("placa"));
                                    vehiculo.setPropietario(rs.getString("propietario"));
                                    vehiculo.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                                    vehiculo.setHoraEntrada(rs.getString("hora_entrada"));
                                    vehiculo.setHoraSalida(rs.getString("hora_salida"));
                                    vehiculo.setValorPagado(rs.getDouble("valor_pagado"));
                                    vehiculo.setEstado(rs.getString("estado"));

                                    listaVehiculo.add(vehiculo);
                           }

                  } catch ( SQLException e ) {
                           System.out.println("ERROR AL CONSULTAR VEHICULOS: " + e);
                  }
                  return listaVehiculo;
         }

}
