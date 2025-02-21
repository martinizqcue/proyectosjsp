/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Usuarios;

/**
 *
 * @author alumno
 */
public interface UsuariosDAO {
    Usuarios validarUsuario(String usuario, String contrasena);
}
