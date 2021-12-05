package com.applab.gli.service;

import com.applab.gli.domain.Admin;
import com.applab.gli.dto.AdminDTO;
import com.applab.gli.dto.PagingAdminsDTO;
import com.applab.gli.dto.ResponseDTO;
import com.applab.gli.exception.UploadAdminPhotoException;
import com.applab.gli.mapper.AdminMapper;
import com.applab.gli.repository.AdminRepository;
import com.applab.gli.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.applab.gli.enumeration.ResponseStatus.*;
import static com.applab.gli.enumeration.Status.INACTIVE;
import static com.applab.gli.mapper.AdminMapper.*;
import static com.applab.gli.utils.Messages.*;
import static com.applab.gli.utils.Utils.buildResponseDTO;
import static java.time.LocalDateTime.now;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    final int DEFAULT_PAGE = 0;
    final int DEFAULT_PAGE_SIZE = 10;
    final String DEFAULT_SORT_FIELD = "id";
    final Sort.Direction DEFAULT_SORT_DIRECTION = DESC;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseDTO> getAllAdminsPageable(PagingAdminsDTO pagingAdminsDTO) {
        Pageable page = PageRequest.of(
            null != pagingAdminsDTO.getPage() ? pagingAdminsDTO.getPage() : DEFAULT_PAGE,
            null != pagingAdminsDTO.getPageSize() ? pagingAdminsDTO.getPageSize() : DEFAULT_PAGE_SIZE,
            null != pagingAdminsDTO.getSortDirection() ?
                    !Utils.isNullOrBlank(pagingAdminsDTO.getSortField()) ? Sort.by(pagingAdminsDTO.getSortDirection(), pagingAdminsDTO.getSortField()) : Sort.by(pagingAdminsDTO.getSortDirection(), DEFAULT_SORT_FIELD)
                    : !Utils.isNullOrBlank(pagingAdminsDTO.getSortField()) ? Sort.by(DEFAULT_SORT_DIRECTION, pagingAdminsDTO.getSortField()) : Sort.by(DEFAULT_SORT_DIRECTION, DEFAULT_SORT_FIELD)
        );
        Page<Admin> adminPage = adminRepository.findAll(page);

        if (!Utils.isNullOrBlank(pagingAdminsDTO.getName())) {
            if (null != pagingAdminsDTO.getAreaId() && null != pagingAdminsDTO.getStatus()) {
                adminPage = adminRepository.findByFullNameAndAreaAndStatus(pagingAdminsDTO.getName(), pagingAdminsDTO.getAreaId(), pagingAdminsDTO.getStatus(), page);
            } else if (null != pagingAdminsDTO.getAreaId()) {
                adminPage = adminRepository.findByFullNameAndArea(pagingAdminsDTO.getName(), pagingAdminsDTO.getAreaId(), page);
            } else if (null != pagingAdminsDTO.getStatus()) {
                adminPage = adminRepository.findByFullNameAndStatus(pagingAdminsDTO.getName(), pagingAdminsDTO.getStatus(), page);
            } else {
                adminPage = adminRepository.findByFullName(pagingAdminsDTO.getName(), page);
            }
        } else {
            if (null != pagingAdminsDTO.getAreaId() && null != pagingAdminsDTO.getStatus()) {
                adminPage = adminRepository.findByAreaAndStatus(pagingAdminsDTO.getAreaId(), pagingAdminsDTO.getStatus(), page);
            } else if (null != pagingAdminsDTO.getAreaId()) {
                adminPage = adminRepository.findByArea(pagingAdminsDTO.getAreaId(), page);
            } else if (null != pagingAdminsDTO.getStatus()) {
                adminPage = adminRepository.findByStatus(pagingAdminsDTO.getStatus(), page);
            }
        }

        List<AdminDTO> adminList = adminPage
            .stream()
            .map(AdminMapper::toAdminDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(
            buildResponseDTO(
                ADMINS_RETRIEVED,
                SUCCESS,
                Map.of("admins", new PageImpl(adminList, page, adminPage.getTotalElements()))
            )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseDTO> getAdminById(Long adminId) {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);

        String adminWithIdMsg = String.format(optionalAdmin.isPresent() ? ADMIN_WITH_ID_RETRIEVED : ADMIN_WITH_ID_NOT_FOUND, adminId);

        return ResponseEntity.ok(
            ResponseDTO.builder()
                .timeStamp(now())
                .message(adminWithIdMsg)
                .data(optionalAdmin.map(admin -> Map.of("admin", toAdminDTO(admin))).orElse(null))
                .build()
        );
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDTO> saveAdmin(AdminDTO adminDTO) {
        Admin admin = toAdmin(adminDTO);
        admin.setCreatedAt(now());

        adminRepository.save(admin);

        final String adminWithIdCreatedMsg = String.format(ADMIN_WITH_ID_CREATED, admin.getId());

        log.info(adminWithIdCreatedMsg);

        return ResponseEntity.ok(
            buildResponseDTO(
                adminWithIdCreatedMsg,
                CREATED,
                Map.of("post", toAdminDTO(admin))
            )
        );
    }

    @Override
    public ResponseEntity<ResponseDTO> uploadAdminPhoto(Long adminId, MultipartFile adminPhoto) throws UploadAdminPhotoException {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);

        ResponseDTO responseDTO;
        final String adminWithIdMsg = String.format(optionalAdmin.isPresent() ? ADMIN_WITH_ID_PHOTO_UPLOADED : ADMIN_WITH_ID_NOT_FOUND, adminId);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            try {
                admin.setPhoto(adminPhoto.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                throw new UploadAdminPhotoException("An error has occurred trying save admins photo");
            }

            admin.setUpdatedAt(now());
            admin = adminRepository.save(admin);

            responseDTO =
                buildResponseDTO(
                    adminWithIdMsg,
                    UPLOADED,
                    Map.of("admin", toAdminDTO(admin))
                );
        } else {
            responseDTO =
                buildResponseDTO(
                        adminWithIdMsg,
                        NOT_FOUND
                );
        }
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateAdmin(Long adminId, AdminDTO adminDTO) {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);

        ResponseDTO responseDTO;
        final String adminWithIdMsg = String.format(optionalAdmin.isPresent() ? ADMIN_WITH_ID_UPDATED : ADMIN_WITH_ID_NOT_FOUND, adminId);

        if (optionalAdmin.isPresent()) {
            Admin admin = toAdmin(adminDTO, optionalAdmin.get());
            admin.setUpdatedAt(now());
            admin = adminRepository.save(admin);

            responseDTO =
                buildResponseDTO(
                    adminWithIdMsg,
                    UPDATED,
                    Map.of("admin", toAdminDTO(admin))
                );
        } else {
            responseDTO =
                buildResponseDTO(
                    adminWithIdMsg,
                    NOT_FOUND
                );
        }
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDTO> deleteAdmin(Long adminId) {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);

        ResponseDTO responseDTO;
        final String adminWithIdMsg = String.format(optionalAdmin.isPresent() ? ADMIN_WITH_ID_DELETED : ADMIN_WITH_ID_NOT_FOUND, adminId);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setStatus(INACTIVE);
            admin.setDeletedAt(now());
            adminRepository.save(admin);

            responseDTO =
                buildResponseDTO(
                    adminWithIdMsg,
                    DELETED,
                    Map.of("admin", toAdminDTO(admin))
                );
        } else {
            responseDTO =
                buildResponseDTO(
                    adminWithIdMsg,
                    NOT_FOUND
            );
        }

        log.info(adminWithIdMsg);

        return ResponseEntity.ok(responseDTO);
    }

}
