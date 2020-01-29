package generate;

import generate.PastemeAdminAnnounce;

public interface PastemeAdminAnnounceDao {
    int deleteByPrimaryKey(Long id);

    int insert(PastemeAdminAnnounce record);

    int insertSelective(PastemeAdminAnnounce record);

    PastemeAdminAnnounce selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PastemeAdminAnnounce record);

    int updateByPrimaryKey(PastemeAdminAnnounce record);
}