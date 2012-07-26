package com.advanceweb.afc.jb.common;

import java.util.List;


public class MetaSearchTypeDTO {
	

		private int searchTypeId;

		private String searchTypeName;
		
		private List<MetaSearchInputDTO> metaSearchInputDTO;

		private List<MetaSearchParamDTO> metaSearchParamDTO;

		public int getSearchTypeId() {
			return searchTypeId;
		}

		public void setSearchTypeId(int searchTypeId) {
			this.searchTypeId = searchTypeId;
		}

		public String getSearchTypeName() {
			return searchTypeName;
		}

		public void setSearchTypeName(String searchTypeName) {
			this.searchTypeName = searchTypeName;
		}

		

}
