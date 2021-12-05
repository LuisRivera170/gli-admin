package com.applab.gli.mapper;

import com.applab.gli.domain.Area;
import com.applab.gli.dto.AreaDTO;

import static com.applab.gli.utils.Utils.*;

public class AreaMapper {

    public static Area toArea(AreaDTO areaDTO) {
        Area newArea = new Area();
        if (null != areaDTO.getAreaId()) {
            newArea.setId(areaDTO.getAreaId());
        }
        if (!isNullOrBlank(areaDTO.getAreaDescription())) {
            newArea.setDescription(areaDTO.getAreaDescription());
        }
        return newArea;
    }

    public static AreaDTO toAreaDTO(Area area) {
        AreaDTO newAreaDTO = new AreaDTO();
        if (null != area.getId()) {
            newAreaDTO.setAreaId(area.getId());
        }
        if (!isNullOrBlank(area.getDescription())) {
            newAreaDTO.setAreaDescription(area.getDescription());
        }
        return newAreaDTO;
    }

}
