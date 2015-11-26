package com.students.services.dao;

import com.students.domain.Student;
import com.students.services.StudentsService;
import com.students.services.common.CompareOperator;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by kkolesnichenko on 11/25/2015.
 */
public class StudentsDaoServiceImpl extends GenericJpaDao<Student, Long> implements StudentsService{

    private static final String STUDENDS_WITH_SCORE="select s from Student s " +
                                                    "join s.courses c "  +
                                                    "where index(c) in (select cc from Course cc where cc.id = :courseId) " +
                                                    "and c " ;

    public StudentsDaoServiceImpl() {
        super(Student.class);
    }

    @Override
    public Collection<Student> findStudentWithScore(Long courseId, BigDecimal score, CompareOperator operator) {
        String sql=STUDENDS_WITH_SCORE + operator.sqlOperator()+" "+score;
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }
}
