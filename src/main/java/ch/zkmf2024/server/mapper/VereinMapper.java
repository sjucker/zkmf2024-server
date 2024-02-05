package ch.zkmf2024.server.mapper;

import ch.zkmf2024.server.dto.KontaktDTO;
import ch.zkmf2024.server.dto.TitelDTO;
import ch.zkmf2024.server.dto.VereinsangabenDTO;
import ch.zkmf2024.server.dto.VereinsanmeldungDTO;
import ch.zkmf2024.server.dto.VereinsanmeldungDetailDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinAnmeldungDetailPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VereinMapper {

    VereinMapper INSTANCE = Mappers.getMapper(VereinMapper.class);

    VereinsangabenDTO toDTO(VereinPojo pojo);

    KontaktDTO toDTO(KontaktPojo pojo);

    @Mapping(target = "pflichtStueck", ignore = true)
    TitelDTO toDTO(TitelPojo pojo);

    @Mapping(target = "modul", ignore = true)
    @Mapping(target = "klasse", ignore = true)
    @Mapping(target = "besetzung", ignore = true)
    TitelPojo toPojo(TitelDTO dto, Long fkVerein);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fkVerein", ignore = true)
    @Mapping(target = "modul", ignore = true)
    @Mapping(target = "klasse", ignore = true)
    @Mapping(target = "besetzung", ignore = true)
    void updatePojo(@MappingTarget TitelPojo pojo, TitelDTO dto);

    @Mapping(target = "klasseModulA", source = "klasseModula")
    @Mapping(target = "klasseModulB", source = "klasseModulb")
    @Mapping(target = "klasseModulH", source = "klasseModulh")
    @Mapping(target = "modulA", source = "modula")
    @Mapping(target = "modulB", source = "modulb")
    @Mapping(target = "modulC", source = "modulc")
    @Mapping(target = "modulD", source = "moduld")
    @Mapping(target = "modulE", source = "module")
    @Mapping(target = "modulF", source = "modulf")
    @Mapping(target = "modulG", source = "modulg")
    @Mapping(target = "modulH", source = "modulh")
    VereinsanmeldungDTO toVereinsanmeldungDTO(VereinPojo pojo);

    @Mapping(target = "verpflegungHelper1", source = "verpflegungHelper_1")
    @Mapping(target = "verpflegungHelper2", source = "verpflegungHelper_2")
    @Mapping(target = "verpflegungHelper3", source = "verpflegungHelper_3")
    @Mapping(target = "verpflegungHelper4", source = "verpflegungHelper_4")
    @Mapping(target = "verpflegungHelper5", source = "verpflegungHelper_5")
    @Mapping(target = "verpflegungHelper6", source = "verpflegungHelper_6")
    VereinsanmeldungDetailDTO toAnmeldungDetailDto(VereinAnmeldungDetailPojo pojo);

    @Mapping(target = "tambouren", ignore = true)
    @Mapping(target = "praesidentKontaktId", ignore = true)
    @Mapping(target = "perkussionsensemble", ignore = true)
    @Mapping(target = "modulh", ignore = true)
    @Mapping(target = "modulg", ignore = true)
    @Mapping(target = "modulf", ignore = true)
    @Mapping(target = "module", ignore = true)
    @Mapping(target = "moduld", ignore = true)
    @Mapping(target = "modulc", ignore = true)
    @Mapping(target = "modulb", ignore = true)
    @Mapping(target = "modula", ignore = true)
    @Mapping(target = "klasseModulh", ignore = true)
    @Mapping(target = "klasseModulb", ignore = true)
    @Mapping(target = "klasseModula", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "harmonie", ignore = true)
    @Mapping(target = "fanfare", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "direktionKontaktId", ignore = true)
    @Mapping(target = "brassBand", ignore = true)
    @Mapping(target = "websiteText", ignore = true)
    @Mapping(target = "confirmedAt", ignore = true)
    @Mapping(target = "tambourenKatA", ignore = true)
    @Mapping(target = "tambourenKatB", ignore = true)
    @Mapping(target = "tambourenKatC", ignore = true)
    void updateVereinsangaben(@MappingTarget VereinPojo pojo, VereinsangabenDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateKontakt(@MappingTarget KontaktPojo pojo, KontaktDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fkVerein", ignore = true)
    @Mapping(target = "verpflegungHelper_1", source = "verpflegungHelper1")
    @Mapping(target = "verpflegungHelper_2", source = "verpflegungHelper2")
    @Mapping(target = "verpflegungHelper_3", source = "verpflegungHelper3")
    @Mapping(target = "verpflegungHelper_4", source = "verpflegungHelper4")
    @Mapping(target = "verpflegungHelper_5", source = "verpflegungHelper5")
    @Mapping(target = "verpflegungHelper_6", source = "verpflegungHelper6")
    void updateAnmeldungDetail(@MappingTarget VereinAnmeldungDetailPojo pojo, VereinsanmeldungDetailDTO dto);

    default void updateVereinsanmeldung(@MappingTarget VereinPojo pojo, VereinsanmeldungDTO dto) {
        pojo.setModula(dto.modulA());
        pojo.setModulb(dto.modulB());
        pojo.setModulc(dto.modulC());
        pojo.setModuld(dto.modulD());
        pojo.setModule(dto.modulE());
        pojo.setModulf(dto.modulF());
        pojo.setModulg(dto.modulG());
        pojo.setModulh(dto.modulH());

        pojo.setKlasseModula(dto.modulA() ? dto.klasseModulA().name() : null);
        pojo.setKlasseModulb(dto.modulB() ? dto.klasseModulB().name() : null);
        pojo.setKlasseModulh(dto.modulH() ? dto.klasseModulH().name() : null);

        pojo.setHarmonie((dto.modulA() || dto.modulB() || dto.modulC() || dto.modulD() || dto.modulE() || dto.modulF()) && dto.harmonie());
        pojo.setBrassBand((dto.modulA() || dto.modulB() || dto.modulC() || dto.modulD() || dto.modulE() || dto.modulF()) && dto.brassBand());
        pojo.setFanfare((dto.modulA() || dto.modulB() || dto.modulC() || dto.modulD() || dto.modulE() || dto.modulF()) && dto.fanfare());
        pojo.setTambouren(dto.modulG() && dto.tambouren());
        pojo.setPerkussionsensemble((dto.modulC() || dto.modulH()) && dto.perkussionsensemble());

        pojo.setTambourenKatA(dto.modulG() && dto.tambourenKatA());
        pojo.setTambourenKatB(dto.modulG() && dto.tambourenKatB());
        pojo.setTambourenKatC(dto.modulG() && dto.tambourenKatC());
    }

}
