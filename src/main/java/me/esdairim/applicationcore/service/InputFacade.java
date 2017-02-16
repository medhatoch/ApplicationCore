/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.esdairim.applicationcore.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.esdairim.applicationcore.bean.Input;

/**
 *
 * @author lenovo
 */
@Stateless
public class InputFacade extends AbstractFacade<Input> {

    @PersistenceContext(unitName = "ApplicationCorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InputFacade() {
        super(Input.class);
    }
    
}
