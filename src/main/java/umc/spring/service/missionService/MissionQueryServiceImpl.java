package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.missionRepository.MissionRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.MissionResponseDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {


    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> getStoreMission(Long storeId, Integer page) {

        Store currentStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        return missionRepository.findAllByStore(currentStore, PageRequest.of(page, 10));
    }
}
