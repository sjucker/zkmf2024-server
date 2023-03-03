package ch.zkmf2024.server.mapper;

import ch.zkmf2024.server.dto.KontaktDTO;
import ch.zkmf2024.server.dto.VereinsangabenDTO;
import ch.zkmf2024.server.dto.VereinsanmeldungDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VereinMapper {

    VereinMapper INSTANCE = Mappers.getMapper(VereinMapper.class);

    VereinsangabenDTO toVereinsangabenDTO(VereinPojo pojo);

    KontaktDTO toKontaktDTO(KontaktPojo pojo);

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
    void updateVereinsangaben(@MappingTarget VereinPojo pojo, VereinsangabenDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateKontakt(@MappingTarget KontaktPojo pojo, KontaktDTO dto);

    @Mapping(target = "vereinsname", ignore = true)
    @Mapping(target = "praesidentKontaktId", ignore = true)
    @Mapping(target = "plz", ignore = true)
    @Mapping(target = "ort", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "iban", ignore = true)
    @Mapping(target = "homepage", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "direktionKontaktId", ignore = true)
    @Mapping(target = "adresse", ignore = true)
    @Mapping(target = "klasseModula", source = "klasseModulA")
    @Mapping(target = "klasseModulb", source = "klasseModulB")
    @Mapping(target = "klasseModulh", source = "klasseModulH")
    @Mapping(target = "modula", source = "modulA")
    @Mapping(target = "modulb", source = "modulB")
    @Mapping(target = "modulc", source = "modulC")
    @Mapping(target = "moduld", source = "modulD")
    @Mapping(target = "module", source = "modulE")
    @Mapping(target = "modulf", source = "modulF")
    @Mapping(target = "modulg", source = "modulG")
    @Mapping(target = "modulh", source = "modulH")
    void updateVereinsanmeldung(@MappingTarget VereinPojo pojo, VereinsanmeldungDTO dto);

}
