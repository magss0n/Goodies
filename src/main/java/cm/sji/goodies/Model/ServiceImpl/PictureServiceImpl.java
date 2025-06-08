package cm.sji.goodies.Model.ServiceImpl;

import cm.sji.goodies.Model.Entities.Picture;
import cm.sji.goodies.Model.Repository.PictureRepository;
import cm.sji.goodies.Model.Services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> getPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public void savePicture(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public Picture getPicture(Long id) {
        return pictureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Picture> getPicturesByProduct(Long productId) {
        return pictureRepository.findByProduct_Id(productId);
    }
}
