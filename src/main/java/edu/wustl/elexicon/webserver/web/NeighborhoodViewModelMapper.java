package edu.wustl.elexicon.webserver.web;

public enum NeighborhoodViewModelMapper {

    WORD ("word", "Word", null, null),
    ORTHO_WORD ("ortho_word", "Neighbor", null, null);


    private final String columnName;

    public static NeighborhoodViewModelMapper getByMinConstraint(String minConstraintName) {
        for (NeighborhoodViewModelMapper value : NeighborhoodViewModelMapper.values()){
            if (value.minConstraintName != null && value.minConstraintName.equals(minConstraintName)){
                return value;
            }
        }
        return null;
    }

    public static NeighborhoodViewModelMapper getByMaxConstraint(String maxConstraintName) {
        for (NeighborhoodViewModelMapper value : NeighborhoodViewModelMapper.values()){
            if (value.maxConstraintName != null && value.maxConstraintName.equals(maxConstraintName)){
                return value;
            }
        }
        return null;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMinConstraintName() {
        return minConstraintName;
    }

    public String getMaxConstraintName() {
        return maxConstraintName;
    }

    private final String fieldName;
    private final String minConstraintName;
    private final String maxConstraintName;


    NeighborhoodViewModelMapper(String columnName, String fieldName, String minConstraintName, String maxConstraintName){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.minConstraintName = minConstraintName;
        this.maxConstraintName = maxConstraintName;
    }



}
