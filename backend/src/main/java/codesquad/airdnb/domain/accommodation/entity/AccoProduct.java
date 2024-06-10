package codesquad.airdnb.domain.accommodation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCO_PRODUCT")
public class AccoProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCO_ID")
    private Accommodation accommodation;

    private Date reserveDate;

    private Long price;

    @Column(name = "IS_RESERVED")
    private boolean isReserved;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
