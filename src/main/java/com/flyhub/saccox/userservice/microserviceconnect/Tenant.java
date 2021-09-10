package com.flyhub.saccox.userservice.microserviceconnect;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class Tenant {

	private UUID tenantGlobalId;

	private Integer tenantAid;

	private String tenantId;

	private UUID typeGlobalId;

	private String tenantCode;

	private String name;

	private String	alias;

	private String nameLocal;

	private String yearFounded;

	private Date dateFounded;

	private String License;

	private String primaryPhone;

	private String secondaryPhone;

	private String primaryEmail;

	private String secondaryEmail;

	private Boolean	hasWebsite;

	private String	officialWebsiteUrlPrimary;

	private String	officialWebsiteUrlSecondary;

	private String	internalWebsiteUrlPrimary;

	private String internalWebsiteUrlSecondary;

	private String typeName;

	private UUID countryGlobalId;

	private String countryName;

	private UUID districtGlobalId;

	private String districtName;

	private String villageGlobalId;

	private String villageName;

	private String tenantStatus;

	private String categoryGlobalId;

	private String classificationGlobalId;

	private String isInternational;

	private String codeInt;

	private String Motto;

	private String slogan;

	private String mission;

	private String vision;

	private String purpose;

	private String logoSmallUrl;

	private String logoLargeUrl;

	private Byte logoSmall;

	private Byte logoLarge;

	private boolean isRequest;

	private Integer statusId;

	private String status;

	private String displaySeqNo;

	private int isActive;

	private LocalDateTime createdOn;

	private LocalDateTime updatedOn;

	private UUID createdBy;

	private UUID modifiedBy;

	private int softDelete;

	private int hardDelete;
}
