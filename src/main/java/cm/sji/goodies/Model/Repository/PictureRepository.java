package cm.sji.goodies.Model.Repository;

import cm.sji.goodies.Model.Entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findByProduct_Id(Long productId);
}
