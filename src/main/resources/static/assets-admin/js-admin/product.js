// Ad product Modal
// Initialize any custom select plugins if needed
        document.addEventListener('DOMContentLoaded', function() {
            // You might need to initialize select plugins here if you're using any
            
            // Example form submission handler (prevent default refresh)
            const form = document.getElementById('addProductForm');
            const submitBtn = document.querySelector('.btn-submit');
            
            if (submitBtn && form) {
                submitBtn.addEventListener('click', function() {
                    // Here you would typically handle form submission
                    // For now, just close the modal as an example
                    const modal = bootstrap.Modal.getInstance(document.getElementById('addProductModal'));
                    modal.hide();
                    
                    // You would typically handle form data submission here
                    console.log('Form submitted');
                });
            }
        });

        // Add Category style
         document.addEventListener('DOMContentLoaded', function() {
            // File upload preview handling
            const fileInput = document.querySelector('input[type="file"]');
            
            if (fileInput) {
                fileInput.addEventListener('change', function(e) {
                    if (e.target.files && e.target.files[0]) {
                        const reader = new FileReader();
                        const uploadSection = this.closest('.image-upload').querySelector('.image-uploads');
                        
                        reader.onload = function(e) {
                            // Clear current content
                            uploadSection.innerHTML = '';
                            
                            // Create preview image
                            const img = document.createElement('img');
                            img.src = e.target.result;
                            img.alt = 'Uploaded Preview';
                            img.style.maxWidth = '100%';
                            img.style.maxHeight = '200px';
                            
                            // Add filename text
                            const filename = document.createElement('h4');
                            filename.textContent = fileInput.files[0].name;
                            
                            // Append to upload section
                            uploadSection.appendChild(img);
                            uploadSection.appendChild(filename);
                        }
                        
                        reader.readAsDataURL(e.target.files[0]);
                    }
                });
            }
            
            // Form submission handling
            const submitBtn = document.querySelector('.btn-submit');
            
            if (submitBtn) {
                submitBtn.addEventListener('click', function() {
                    // Here you would typically handle form submission
                    // For now, just close the modal as an example
                    const modal = bootstrap.Modal.getInstance(document.getElementById('addCategoryModal'));
                    modal.hide();
                    
                    // You would typically handle form data submission here
                    console.log('Category form submitted');
                });
            }
        });

        // Update Category js
        document.addEventListener('DOMContentLoaded', function() {
            // File upload preview handling
            const fileInput = document.getElementById('categoryImage');
            
            if (fileInput) {
                fileInput.addEventListener('change', function(e) {
                    if (e.target.files && e.target.files[0]) {
                        const reader = new FileReader();
                        const uploadSection = this.closest('.image-upload').querySelector('.image-uploads');
                        
                        reader.onload = function(e) {
                            // Clear current content
                            uploadSection.innerHTML = '';
                            
                            // Create preview image
                            const img = document.createElement('img');
                            img.src = e.target.result;
                            img.alt = 'Uploaded Preview';
                            img.style.maxWidth = '100%';
                            img.style.maxHeight = '150px';
                            
                            // Add filename text
                            const filename = document.createElement('h4');
                            filename.textContent = fileInput.files[0].name;
                            
                            // Append to upload section
                            uploadSection.appendChild(img);
                            uploadSection.appendChild(filename);
                        }
                        
                        reader.readAsDataURL(e.target.files[0]);
                    }
                });
            }
            
            // Edit Category form submission handling
            const updateBtn = document.getElementById('updateCategory');
            
            if (updateBtn) {
                updateBtn.addEventListener('click', function() {
                    // Here you would typically handle form submission with updated data
                    const categoryName = document.getElementById('categoryName').value;
                    const categoryCode = document.getElementById('categoryCode').value;
                    const categoryDescription = document.getElementById('categoryDescription').value;
                    
                    // Example of collecting form data
                    const formData = {
                        name: categoryName,
                        code: categoryCode,
                        description: categoryDescription,
                        // You would handle file upload here
                    };
                    
                    console.log('Category data updated:', formData);
                    
                    // Close the modal
                    const modal = bootstrap.Modal.getInstance(document.getElementById('editCategoryModal'));
                    modal.hide();
                    
                    // You would typically handle the API call to update the category here
                });
            }
            
            // This function would be called when the edit icon is clicked to load category data
            function loadCategoryData(categoryId) {
                // In a real application, you would fetch the category data from your API
                // For this example, we're using placeholder data already in the HTML
                
                // Example of how you might load data from an API:
                /*
                fetch('/api/categories/' + categoryId)
                    .then(response => response.json())
                    .then(data => {
                        document.getElementById('categoryName').value = data.name;
                        document.getElementById('categoryCode').value = data.code;
                        document.getElementById('categoryDescription').value = data.description;
                        
                        // Set current image
                        const currentImage = document.querySelector('#currentImage img');
                        if (currentImage && data.imageUrl) {
                            currentImage.src = data.imageUrl;
                        }
                    })
                    .catch(error => console.error('Error loading category data:', error));
                */
            }
            
            // Example of setting up event listeners for edit buttons across multiple categories
            document.querySelectorAll('[data-bs-toggle="modal"][data-bs-target="#editCategoryModal"]').forEach(button => {
                button.addEventListener('click', function() {
                    // You would get the category ID from a data attribute or similar
                    const categoryId = this.getAttribute('data-category-id') || 1;
                    loadCategoryData(categoryId);
                });
            });
        });


        // Add brand modal
         document.addEventListener('DOMContentLoaded', function() {
            // File upload preview handling
            const fileInput = document.getElementById('brandImage');
            
            if (fileInput) {
                fileInput.addEventListener('change', function(e) {
                    if (e.target.files && e.target.files[0]) {
                        const reader = new FileReader();
                        const uploadSection = this.closest('.image-upload').querySelector('.image-uploads');
                        
                        reader.onload = function(e) {
                            // Clear current content
                            uploadSection.innerHTML = '';
                            
                            // Create preview image
                            const img = document.createElement('img');
                            img.src = e.target.result;
                            img.alt = 'Uploaded Preview';
                            img.style.maxWidth = '100%';
                            img.style.maxHeight = '150px';
                            
                            // Add filename text
                            const filename = document.createElement('h4');
                            filename.textContent = fileInput.files[0].name;
                            
                            // Append to upload section
                            uploadSection.appendChild(img);
                            uploadSection.appendChild(filename);
                        }
                        
                        reader.readAsDataURL(e.target.files[0]);
                    }
                });
            }
            
            // Add Brand form submission handling
            const submitBtn = document.getElementById('saveBrand');
            
            if (submitBtn) {
                submitBtn.addEventListener('click', function() {
                    // Get form values
                    const brandName = document.querySelector('#addBrandForm input[type="text"]').value;
                    const description = document.querySelector('#addBrandForm textarea').value;
                    
                    // Example of collecting form data
                    const formData = {
                        name: brandName,
                        description: description,
                        // You would handle file upload here
                    };
                    
                    console.log('Brand form submitted:', formData);
                    
                    // Close the modal
                    const modal = bootstrap.Modal.getInstance(document.getElementById('addBrandModal'));
                    modal.hide();
                    
                    // You would typically handle the API call to save the brand here
                });
            }
        });


        // Edit brand modal
         document.addEventListener('DOMContentLoaded', function() {
            // File upload preview handling
            const fileInput = document.getElementById('brandImage');
            
            if (fileInput) {
                fileInput.addEventListener('change', function(e) {
                    if (e.target.files && e.target.files[0]) {
                        const reader = new FileReader();
                        const uploadSection = this.closest('.image-upload').querySelector('.image-uploads');
                        
                        reader.onload = function(e) {
                            // Clear current content
                            uploadSection.innerHTML = '';
                            
                            // Create preview image
                            const img = document.createElement('img');
                            img.src = e.target.result;
                            img.alt = 'Uploaded Preview';
                            img.style.maxWidth = '100%';
                            img.style.maxHeight = '150px';
                            
                            // Add filename text
                            const filename = document.createElement('h4');
                            filename.textContent = fileInput.files[0].name;
                            
                            // Append to upload section
                            uploadSection.appendChild(img);
                            uploadSection.appendChild(filename);
                        }
                        
                        reader.readAsDataURL(e.target.files[0]);
                    }
                });
            }
            
            // Edit Brand form submission handling
            const updateBtn = document.getElementById('updateBrand');
            
            if (updateBtn) {
                updateBtn.addEventListener('click', function() {
                    // Get form values
                    const brandName = document.getElementById('brandName').value;
                    const description = document.getElementById('brandDescription').value;
                    
                    // Example of collecting form data
                    const formData = {
                        name: brandName,
                        description: description,
                        // You would handle file upload here
                    };
                    
                    console.log('Brand updated:', formData);
                    
                    // Close the modal
                    const modal = bootstrap.Modal.getInstance(document.getElementById('editBrandModal'));
                    modal.hide();
                    
                    // You would typically handle the API call to update the brand here
                });
            }
            
            // Function to load brand data when edit icon is clicked
            function loadBrandData(brandId) {
                // In a real application, you would fetch the brand data from your API
                // For this example, we're using placeholder data already in the HTML
                
                // Example of how you might load data from an API:
                /*
                fetch('/api/brands/' + brandId)
                    .then(response => response.json())
                    .then(data => {
                        document.getElementById('brandName').value = data.name;
                        document.getElementById('brandDescription').value = data.description;
                        
                        // Set current image
                        const currentImage = document.querySelector('#currentBrandImage img');
                        if (currentImage && data.imageUrl) {
                            currentImage.src = data.imageUrl;
                        }
                    })
                    .catch(error => console.error('Error loading brand data:', error));
                */
            }
            
            // Set up event listeners for edit icons
            document.querySelectorAll('[data-bs-toggle="modal"][data-bs-target="#editBrandModal"]').forEach(button => {
                button.addEventListener('click', function() {
                    // Get the brand ID from the data attribute
                    const brandId = this.getAttribute('data-brand-id');
                    loadBrandData(brandId);
                });
            });
        });