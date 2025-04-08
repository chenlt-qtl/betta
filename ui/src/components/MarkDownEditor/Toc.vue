<template>
    <div class="toc-container">
      <div 
        v-for="heading in headings"
        :key="heading.id"
        :class="['toc-item', `toc-level-${heading.level}`]"
      >
        <a @click="scrollTo(heading.id)">{{ heading.text }}</a>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      headings: {
        type: Array,
        required: true
      }
    },
    methods: {
      scrollTo(id) {
        const editorContent = document.querySelector('.toastui-editor-contents');
        const target = editorContent.querySelector(`#${id}`);
        
        if (target) {
          target.scrollIntoView({
            behavior: 'smooth',
            block: 'start'
          });
        }
      }
    }
  };
  </script>
  
  <style>
  .toc-container {
    position: sticky;
  }
  
  .toc-title {
    font-size: 14px;
    font-weight: 600;
    margin-bottom: 6px;
    color: #333;
  }
  
  .toc-item {
    transition: all 0.2s;
  }
  
  .toc-item a {
    color: #666;
    text-decoration: none;
    cursor: pointer;
    display: block;
    padding: 4px 8px;
    border-radius: 4px;
  }
  
  .toc-item a:hover {
    color: #2196f3;
    background: #f5f5f5;
  }
  
  .toc-level-1 { margin-left: 0; }
  .toc-level-2 { margin-left: 16px; }
  .toc-level-3 { margin-left: 32px; }
  .toc-level-4 { margin-left: 48px; }
  </style>