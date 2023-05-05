package com.example.demo.model.sample;
import com.example.demo.constant.CommonEnums;
import com.example.demo.model.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sample")
@Data
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long nameId;
    private Long priceId;
    private Long serviceId;
    private Long otherId;
    private Long userId;

    private int status = CommonEnums.SampleStatus.未完成.getValue();


    @Transient
    private String name;

    @Transient
    private Long labelId;

    @Transient
    private List<SampleLabel> labelList;

    @Transient
    private List<Long> labelIds;

    @Transient
    private String description;

    @Transient
    private String coverImgs;

    @Transient
    private String detailImgs;

    @Transient
    private Integer price;

    @Transient
    private Integer earnest;

    @Transient
    private Boolean provideModels;

    @Transient
    private String modelCount;

    @Transient
    private String modelCountCustom;

    @Transient
    private String filmCount;

    @Transient
    private Boolean filmAllSend = false;

    @Transient
    private String shootDuration;

    @Transient
    private String shootDurationCustom;

    @Transient
    private String finishingQuantity;

    @Transient
    private Integer shootSceneIndoorCount;

    @Transient
    private Integer shootSceneOutdoorCount;

    @Transient
    private String customServiceDetail;

    @Transient
    private Boolean isPublic;

    @Transient
    private String tip;

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "created_date")
    protected Date createdDate;

    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @Column(name = "last_modified_date")
    protected Date lastModifiedDate;

}
