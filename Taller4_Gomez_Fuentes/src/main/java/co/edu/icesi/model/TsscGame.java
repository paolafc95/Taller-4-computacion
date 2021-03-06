package co.edu.icesi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.edu.icesi.ci.validations.ValidacionesForm1;

/**
 * The persistent class for the TSSC_GAME database table.
 * 
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "TSSC_GAME")
@NamedQuery(name = "TsscGame.findAll", query = "SELECT t FROM TsscGame t")
public class TsscGame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_GAME_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_GAME_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_GAME_ID_GENERATOR")
	private long id;
	@NotBlank(message = "Ingrese una contraseña de administrador")
	@NotEmpty
	@Column(name = "ADMIN_PASSWORD")
	private String adminPassword;
	@NotBlank(message = "Ingrese una contraseña para invitados")
	@Column(name = "GUEST_PASSWORD")
	private String guestPassword;
	@Positive
	@Column(name = "N_GROUPS")
	private Integer nGroups;
	@Positive
	@Column(name = "N_SPRINTS")
	private Integer nSprints;
	@NotBlank(message = "Ingrese un nombre para el juego")
	@NotEmpty(groups = ValidacionesForm1.class)
	private String name;

	@Column(name = "PAUSE_SECONDS")
	private Long pauseSeconds = 0L;
	
	@Column(name = "SCHEDULED_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate scheduledDate;
	
	@Column(name = "SCHEDULED_TIME")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime scheduledTime;

	@Column(name = "START_TIME")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;
	
	@Column(name = "TYPEGAME_ID")
	private BigDecimal typegameId;
	@NotBlank(message = "Ingrese una contraseña para usuario")
	@Column(name = "USER_PASSWORD")
	private String userPassword;

	// bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name = "TSSC_STATE_ID")
	private TsscState tsscState;

	// bi-directional many-to-one association to TsscGameAdmin
	@OneToMany(mappedBy = "tsscGame")
	private List<TsscGameAdmin> tsscGameAdmins;

	// bi-directional many-to-one association to TsscGroup
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tsscGame")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<TsscGroup> tsscGroups;

	// bi-directional many-to-one association to TsscSprint
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tsscGame")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<TsscSprint> tsscSprints;

	// bi-directional many-to-one association to TsscStory
	@OneToMany(mappedBy = "tsscGame")
	@JsonIgnore
	private List<TsscStory> tsscStories;
	
	//bi-directional many-to-one association to TsscTimecontrol
	@OneToMany(mappedBy="tsscGame")
	private List<TsscTimecontrol> tsscTimecontrols;

	//bi-directional many-to-one association to TssTopic
	@ManyToOne
	@JoinColumn(name="TSSC_TOPIC_ID")
	private TsscTopic tsscTopic;

	public TsscGame() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getGuestPassword() {
		return this.guestPassword;
	}

	public void setGuestPassword(String guestPassword) {
		this.guestPassword = guestPassword;
	}

	public Integer getNGroups() {
		return this.nGroups;
	}

	public void setNGroups(Integer nGroups) {
		this.nGroups = nGroups;
	}

	public Integer getNSprints() {
		return this.nSprints;
	}

	public void setNSprints(Integer nSprints) {
		this.nSprints = nSprints;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPauseSeconds() {
		return this.pauseSeconds;
	}

	public void setPauseSeconds(Long pauseSeconds) {
		this.pauseSeconds = pauseSeconds;
	}

	public LocalDate getScheduledDate() {
		return this.scheduledDate;
	}

	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public LocalTime getScheduledTime() {
		return this.scheduledTime;
	}

	public void setScheduledTime(LocalTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public LocalTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getTypegameId() {
		return this.typegameId;
	}

	public void setTypegameId(BigDecimal typegameId) {
		this.typegameId = typegameId;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public TsscState getTsscState() {
		return this.tsscState;
	}

	public void setTsscState(TsscState tsscState) {
		this.tsscState = tsscState;
	}

	public List<TsscGameAdmin> getTsscGameAdmins() {
		return this.tsscGameAdmins;
	}

	public void setTsscGameAdmins(List<TsscGameAdmin> tsscGameAdmins) {
		this.tsscGameAdmins = tsscGameAdmins;
	}

	public TsscGameAdmin addTsscGameAdmin(TsscGameAdmin tsscGameAdmin) {
		getTsscGameAdmins().add(tsscGameAdmin);
		tsscGameAdmin.setTsscGame(this);

		return tsscGameAdmin;
	}

	public TsscGameAdmin removeTsscGameAdmin(TsscGameAdmin tsscGameAdmin) {
		getTsscGameAdmins().remove(tsscGameAdmin);
		tsscGameAdmin.setTsscGame(null);

		return tsscGameAdmin;
	}

	public List<TsscGroup> getTsscGroups() {
		return this.tsscGroups;
	}

	public void setTsscGroups(List<TsscGroup> tsscGroups) {
		this.tsscGroups = tsscGroups;
	}

	public TsscGroup addTsscGroup(TsscGroup tsscGroup) {
		getTsscGroups().add(tsscGroup);
		tsscGroup.setTsscGame(this);

		return tsscGroup;
	}

	public TsscGroup removeTsscGroup(TsscGroup tsscGroup) {
		getTsscGroups().remove(tsscGroup);
		tsscGroup.setTsscGame(null);

		return tsscGroup;
	}

	public List<TsscSprint> getTsscSprints() {
		return this.tsscSprints;
	}

	public void setTsscSprints(List<TsscSprint> tsscSprints) {
		this.tsscSprints = tsscSprints;
	}

	public TsscSprint addTsscSprint(TsscSprint tsscSprint) {
		getTsscSprints().add(tsscSprint);
		tsscSprint.setTsscGame(this);

		return tsscSprint;
	}

	public TsscSprint removeTsscSprint(TsscSprint tsscSprint) {
		getTsscSprints().remove(tsscSprint);
		tsscSprint.setTsscGame(null);

		return tsscSprint;
	}

	public List<TsscStory> getTsscStories() {
		return this.tsscStories;
	}

	public void setTsscStories(List<TsscStory> tsscStories) {
		this.tsscStories = tsscStories;
	}

	public TsscStory addTsscStory(TsscStory tsscStory) {
		getTsscStories().add(tsscStory);
		tsscStory.setTsscGame(this);

		return tsscStory;
	}

	public TsscStory removeTsscStory(TsscStory tsscStory) {
		getTsscStories().remove(tsscStory);
		tsscStory.setTsscGame(null);

		return tsscStory;
	}

	public List<TsscTimecontrol> getTsscTimecontrols() {
		return this.tsscTimecontrols;
	}

	public void setTsscTimecontrol(List<TsscTimecontrol> tsscTimecontrols) {
		this.tsscTimecontrols = tsscTimecontrols;
	}

	public TsscTimecontrol addTsscTimecontrol(TsscTimecontrol tsscTimecontrol) {
		getTsscTimecontrols().add(tsscTimecontrol);
		tsscTimecontrol.setTsscGame(this);

		return tsscTimecontrol;
	}

	public TsscTimecontrol removeTsscTimecontrol(TsscTimecontrol tsscTimecontrol) {
		getTsscTimecontrols().remove(tsscTimecontrol);
		tsscTimecontrol.setTsscGame(null);

		return tsscTimecontrol;
	}
	
	public TsscTopic getTsscTopic() {
		return this.tsscTopic;
	}

	public void setTsscTopic(TsscTopic tsscTopic) {
		this.tsscTopic = tsscTopic;
	}
	
}