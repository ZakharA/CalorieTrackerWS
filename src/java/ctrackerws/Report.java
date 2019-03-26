/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrackerws;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zakhar
 */
@Entity
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByReportId", query = "SELECT r FROM Report r WHERE r.reportId = :reportId")
    , @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date")
    , @NamedQuery(name = "Report.findByTotalCalorieConsumed", query = "SELECT r FROM Report r WHERE r.totalCalorieConsumed = :totalCalorieConsumed")
    , @NamedQuery(name = "Report.findByTotalCalorieBurned", query = "SELECT r FROM Report r WHERE r.totalCalorieBurned = :totalCalorieBurned")
    , @NamedQuery(name = "Report.findByTotalStepsTaken", query = "SELECT r FROM Report r WHERE r.totalStepsTaken = :totalStepsTaken")
    , @NamedQuery(name = "Report.findByDailyCalorieGoal", query = "SELECT r FROM Report r WHERE r.dailyCalorieGoal = :dailyCalorieGoal")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPORT_ID")
    private Integer reportId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CALORIE_CONSUMED")
    private int totalCalorieConsumed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CALORIE_BURNED")
    private float totalCalorieBurned;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_STEPS_TAKEN")
    private int totalStepsTaken;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAILY_CALORIE_GOAL")
    private int dailyCalorieGoal;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private AppUser userId;

    public Report() {
    }

    public Report(Integer reportId) {
        this.reportId = reportId;
    }

    public Report(Integer reportId, Date date, int totalCalorieConsumed, float totalCalorieBurned, int totalStepsTaken, int dailyCalorieGoal) {
        this.reportId = reportId;
        this.date = date;
        this.totalCalorieConsumed = totalCalorieConsumed;
        this.totalCalorieBurned = totalCalorieBurned;
        this.totalStepsTaken = totalStepsTaken;
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalCalorieConsumed() {
        return totalCalorieConsumed;
    }

    public void setTotalCalorieConsumed(int totalCalorieConsumed) {
        this.totalCalorieConsumed = totalCalorieConsumed;
    }

    public float getTotalCalorieBurned() {
        return totalCalorieBurned;
    }

    public void setTotalCalorieBurned(float totalCalorieBurned) {
        this.totalCalorieBurned = totalCalorieBurned;
    }

    public int getTotalStepsTaken() {
        return totalStepsTaken;
    }

    public void setTotalStepsTaken(int totalStepsTaken) {
        this.totalStepsTaken = totalStepsTaken;
    }

    public int getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public void setDailyCalorieGoal(int dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public AppUser getUserId() {
        return userId;
    }

    public void setUserId(AppUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ctrackerws.Report[ reportId=" + reportId + " ]";
    }
    
}
