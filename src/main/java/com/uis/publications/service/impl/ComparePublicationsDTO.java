/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.service.impl;

import com.uis.publications.dto.PublicationsDTO;

import java.util.Comparator;

public class ComparePublicationsDTO implements Comparator<PublicationsDTO> {
    @Override
    public int compare(PublicationsDTO o1, PublicationsDTO o2) {
        if(o2.getCreated().before(o1.getCreated())){
            return -1;
        }else if (o2.getCreated().before(o1.getCreated())){
            return 0;
        }else {
            return 1;
        }

    }
}
