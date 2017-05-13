/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReduction.findAll", query = "SELECT v FROM VReduction v")})
public class VReduction implements Serializable {

    private static final long serialVersionUID = 1L;

    public VReduction() {
    }
    
}
