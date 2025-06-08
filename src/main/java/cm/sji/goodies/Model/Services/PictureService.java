package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.Entities.Picture;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PictureService {
    List<Picture> getPictures();
    void savePicture(Picture picture);
    Picture getPicture(Long id);
    List<Picture> getPicturesByProduct(Long productId);

}
